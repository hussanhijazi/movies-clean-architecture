package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import br.com.hussan.cubosmovies.domain.Movie
import io.reactivex.Single

class GetMovies(private val dataSource: MovieDatasource) {
    operator fun invoke(genre: Int, page: Int): Single<List<Movie>> {
        return dataSource.getMovies(genre, page)
    }
}
