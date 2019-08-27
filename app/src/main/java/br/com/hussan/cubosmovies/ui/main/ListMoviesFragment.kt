package br.com.hussan.cubosmovies.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.extensions.add
import br.com.hussan.cubosmovies.extensions.hide
import br.com.hussan.cubosmovies.extensions.show
import br.com.hussan.cubosmovies.util.EndlessRecyclerOnScrollListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list_movies.*
import kotlinx.android.synthetic.main.lyt_error_connection.*
import kotlinx.android.synthetic.main.lyt_loading.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListMoviesFragment : Fragment() {

    private lateinit var scrollListener: EndlessRecyclerOnScrollListener
    private var actualPage: Int = 1
    private val viewModelList: ListMoviesViewModel by viewModel()
    private val navigator: AppNavigator by inject { parametersOf(activity) }
    private val compositeDisposable = CompositeDisposable()
    private val movieAdapter by lazy { MoviesAdapter(::goToDetails) }

    private val genre by lazy {
        arguments?.getInt(GENRE)
    }

    companion object {
        val GENRE = "GENRE"
        fun newInstance(genre: Int) = ListMoviesFragment().apply {
            arguments = Bundle().apply {
                putInt(GENRE, genre)
            }
        }
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
        getMovies(1)
    }


    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            movieAdapter.setItems(listOf())
            scrollListener.reset()
            getMovies(1)
        }
    }

    private fun getMovies(page: Int) {
        viewModelList.getMovies(genre ?: return, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .doOnError { showLoading(false) }
            .subscribe(::showProducts, ::showError)
            .add(compositeDisposable)
    }

    private fun showProducts(items: List<MovieView>) {
        Log.d("h2", items.toString())

        if (items.isNotEmpty()) {
            movieAdapter.addItems(items)
            showRecyclerViewProducts()
            if (swipeRefresh.isRefreshing)
                swipeRefresh.isRefreshing = false
        }
    }

    private fun showRecyclerViewProducts() {
        rvProducts.show()
        lytConnectionError.hide()
    }

    private fun showError(error: Throwable) {
        Log.d("h2", error.message)
    }

    private fun showLoading(show: Boolean) {
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
                    // TODO pagination
                }
            }

            addOnScrollListener(scrollListener)
        }
    }

    private fun goToDetails(movie: MovieView) {

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
