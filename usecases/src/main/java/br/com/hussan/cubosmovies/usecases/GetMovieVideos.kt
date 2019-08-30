package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import br.com.hussan.cubosmovies.domain.MovieVideos
import io.reactivex.Single

class GetMovieVideos(private val dataSource: MovieDatasource) {
    operator fun invoke(genre: Int, language: String): Single<MovieVideos> {
        return dataSource.getMovieVideos(genre, language)
    }
}
