package br.com.hussan.cubosmovies.ui.main

import androidx.lifecycle.ViewModel
import br.com.hussan.cubosmovies.data.mapper.MovieViewMapper
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.usecases.GetMovies
import io.reactivex.Single

class ListMoviesViewModel(
    private val getMovies: GetMovies,
    private val mapper: MovieViewMapper
) : ViewModel() {

    fun getMovies(genre: Int, page: Int): Single<List<MovieView>> {
        return getMovies.invoke(genre, page).map {
            it.map(mapper::mapToView)
        }
    }
}

