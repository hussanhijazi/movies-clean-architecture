package br.com.hussan.cubosmovies.ui.search

import androidx.lifecycle.ViewModel
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.usecases.SearchMovies
import io.reactivex.Single

class SearchMoviesViewModel(
    private val searchMovies: SearchMovies,
    private val mapper: MoviesPaginationViewMapper,
    private val language: String
) : ViewModel() {

    fun searchMovies(genre: String, page: Int): Single<MoviesPaginationView> {
        return searchMovies.invoke(genre, page, language).map(mapper::mapToView)
    }
}
