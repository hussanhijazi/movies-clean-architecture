package br.com.hussan.cubosmovies.ui.main

import androidx.lifecycle.ViewModel
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.usecases.GetMovies
import io.reactivex.Single

class ListMoviesViewModel(
    private val getMovies: GetMovies,
    private val mapper: MoviesPaginationViewMapper
) : ViewModel() {

    fun getMovies(genre: Int, page: Int): Single<MoviesPaginationView> {
        return getMovies.invoke(genre, page).map(mapper::mapToView)
    }
}
