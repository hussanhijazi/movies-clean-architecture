package br.com.hussan.cubosmovies.ui.search

import androidx.lifecycle.ViewModel
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.usecases.SearchMovies
import io.reactivex.Single

class SearchMoviesViewModel(
    private val getMovies: SearchMovies,
    private val mapper: MoviesPaginationViewMapper
) : ViewModel() {

    fun getMovies(genre: String, page: Int): Single<MoviesPaginationView> {
        return getMovies.invoke(genre, page).map(mapper::mapToView)
    }
}
