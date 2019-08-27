package br.com.hussan.cubosmovies.data.datasource

import br.com.hussan.cubosmovies.data.AppApi
import br.com.hussan.cubosmovies.data.cache.MovieCache
import br.com.hussan.cubosmovies.domain.Movie
import io.reactivex.Single

class MovieRepository(
    private val api: AppApi,
    private val cache: MovieCache
) : MovieDatasource {
    override fun getMovies(genre: Int, page: Int): Single<List<Movie>> {
        return api.getMovies(genre, page).map { it.results }
    }
}

interface MovieDatasource {
    fun getMovies(genre: Int, page: Int): Single<List<Movie>>
}
