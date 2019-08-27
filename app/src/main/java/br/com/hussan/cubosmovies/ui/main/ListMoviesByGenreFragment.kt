package br.com.hussan.cubosmovies.ui.main

import android.os.Bundle
import br.com.hussan.cubosmovies.extensions.add
import br.com.hussan.cubosmovies.ui.ListMoviesFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMoviesByGenreFragment : ListMoviesFragment() {
    private val viewModelList: ListMoviesViewModel by viewModel()

    private val genre by lazy {
        arguments?.getInt(GENRE)
    }

    companion object {
        val GENRE = "GENRE"
        fun newInstance(genre: Int) = ListMoviesByGenreFragment().apply {
            arguments = Bundle().apply {
                putInt(GENRE, genre)
            }
        }
    }

    override fun getMovies(page: Int) {
        viewModelList.getMovies(genre ?: return, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .doOnError { showLoading(false) }
            .subscribe(::showMovies, ::showError)
            .add(compositeDisposable)
    }

}
