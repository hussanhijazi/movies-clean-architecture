package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import br.com.hussan.cubosmovies.domain.MoviesPagination
import io.reactivex.Single

class SearchMovies(private val dataSource: MovieDatasource) {
    operator fun invoke(query: String, page: Int): Single<MoviesPagination> {
        return dataSource.searchMovies(query, page)
    }
}
