package br.com.hussan.cubosmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.extensions.hide
import br.com.hussan.cubosmovies.extensions.show
import br.com.hussan.cubosmovies.ui.main.MoviesAdapter
import br.com.hussan.cubosmovies.util.EndlessRecyclerOnScrollListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list_movies.*
import kotlinx.android.synthetic.main.lyt_empty_state.*
import kotlinx.android.synthetic.main.lyt_error.*
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
        getMovies(1)
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            movieAdapter.setItems(listOf())
            scrollListener.reset()
            getMovies(1)
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
        lytError.hide()
        rvProducts.hide()
    }

    private fun showRecyclerViewProducts() {
        rvProducts.show()
        lytEmptystate.hide()
        lytError.hide()
    }

    fun showError() {
        lytError.show()
        rvProducts.hide()
        lytEmptystate.hide()
    }

    fun showLoading(show: Boolean) {
        if (show)
            progressBar.show()
        else
            progressBar.hide()
    }

    private fun setupRecyclerViewProducts() {
        rvProducts.run {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false

            val gridLayout = GridLayoutManager(activity, 2)
            layoutManager = gridLayout
            adapter = movieAdapter

            scrollListener = object : EndlessRecyclerOnScrollListener(gridLayout) {
                override fun onLoadMore(page: Int) {
                    actualPage = page
                    if (pagination.totalPages >= page)
                        getMovies(page)
                }
            }

            addOnScrollListener(scrollListener)
        }
    }

    private fun goToDetails(movie: MovieView, view: ImageView, title: TextView) {
        navigator.gotoMovieDetails(movie, view, title)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    abstract fun getMovies(page: Int)
}
