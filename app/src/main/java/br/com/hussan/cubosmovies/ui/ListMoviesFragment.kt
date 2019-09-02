package br.com.hussan.cubosmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.extensions.hide
import br.com.hussan.cubosmovies.extensions.show
import br.com.hussan.cubosmovies.util.EndlessRecyclerOnScrollListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list_movies.*
import kotlinx.android.synthetic.main.lyt_empty_state.*
import kotlinx.android.synthetic.main.lyt_loading.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class ListMoviesFragment : Fragment() {

    private val navigator: AppNavigator by inject { parametersOf(activity) }
    private lateinit var pagination: MoviesPaginationView

    var actualPage: Int = 1
    val compositeDisposable = CompositeDisposable()
    val movieAdapter by lazy { MoviesAdapter(::goToDetails) }

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    companion object {
        const val PAGINATION = "PAGINATION"
        const val MOVIES = "MOVIES"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewProducts()
        setupSwipeRefresh()

        lifecycle.addObserver(RxLifecycleObserver(compositeDisposable))
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            actualPage = 1
            pagination = MoviesPaginationView()
            movieAdapter.setItems(arrayListOf())
            scrollListener.reset()
            getMovies(actualPage)
        }
    }

    fun showMovies(items: MoviesPaginationView) {
        if (items.results.isNotEmpty()) {
            pagination = items
            movieAdapter.addItems(items.results)
            showRecyclerViewProducts()
            if (swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
        } else {
            showEmptyState()
        }
    }

    private fun showEmptyState() {
        lytEmptystate.show()
        rvMovies.hide()
    }

    private fun showRecyclerViewProducts() {
        rvMovies.show()
        lytEmptystate.hide()
    }

    fun showError() {
        rvMovies.hide()
        lytEmptystate.hide()
        Toast.makeText(activity, getString(R.string.msg_error), Toast.LENGTH_LONG).show()
    }

    fun showLoading(show: Boolean) {
        if (show)
            progressBar.show()
        else
            progressBar.hide()
    }

    private fun setupRecyclerViewProducts() {
        rvMovies.run {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false

            val gridLayout = GridLayoutManager(activity, 2)
            layoutManager = gridLayout
            adapter = movieAdapter

            scrollListener = object : EndlessRecyclerOnScrollListener(gridLayout) {
                override fun onLoadMore() {
                    actualPage = pagination.page + 1
                    if (pagination.totalPages >= actualPage)
                        getMovies(actualPage)
                }
            }
            addOnScrollListener(scrollListener)
        }
    }

    private fun goToDetails(movie: MovieView) {
        navigator.gotoMovieDetails(movie)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            pagination =
                savedInstanceState.getParcelable(PAGINATION) ?: return
            val movies = savedInstanceState.getParcelableArrayList<MovieView>(MOVIES)
            movieAdapter.setItems(movies?.toList() ?: listOf())
        } else getMovies(actualPage)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putParcelable(PAGINATION, pagination)
            putParcelableArrayList(MOVIES, ArrayList(movieAdapter.getMovies()))
        }
    }

    abstract fun getMovies(page: Int)
}
