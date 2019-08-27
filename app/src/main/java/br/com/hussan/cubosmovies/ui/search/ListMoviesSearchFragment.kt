package br.com.hussan.cubosmovies.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.extensions.add
import br.com.hussan.cubosmovies.ui.ListMoviesFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMoviesSearchFragment : ListMoviesFragment() {
    private val viewModelList: SearchMoviesViewModel by viewModel()

    private val query by lazy {
        arguments?.getString(QUERY)
    }

    companion object {
        val QUERY = "QUERY"
        fun newInstance(query: String) = ListMoviesSearchFragment().apply {
            arguments = Bundle().apply {
                putString(QUERY, query)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = query
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                navigator.navigateToSearch(query ?: return false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                true
            }
//            R.id.share -> {
////                val sendIntent: Intent = Intent().apply {
////                    action = Intent.ACTION_SEND
////                    putExtra(
////                        Intent.EXTRA_TEXT,
////                        "${movie.title} por R$ ${product.price} na Enjoei"
////                    )
////                    type = "text/plain"
////                }
////                startActivity(
////                    Intent.createChooser(
////                        sendIntent,
////                        resources.getText(R.string.share_msg)
////                    )
////                )
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getMovies(page: Int) {
        viewModelList.getMovies(query ?: return, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .doOnError { showLoading(false) }
            .subscribe(::showMovies, ::showError)
            .add(compositeDisposable)
    }

}
