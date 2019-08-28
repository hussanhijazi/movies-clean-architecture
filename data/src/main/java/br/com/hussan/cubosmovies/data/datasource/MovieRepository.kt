package br.com.hussan.cubosmovies.data.datasource

import br.com.hussan.cubosmovies.data.AppApi
import br.com.hussan.cubosmovies.data.cache.MovieCache
import br.com.hussan.cubosmovies.domain.MoviesPagination
import io.reactivex.Single

class MovieRepository(
    private val api: AppApi,
    private val cache: MovieCache
) : MovieDatasource {

    override fun searchMovies(query: String, page: Int): Single<MoviesPagination> {
        return api.searchMovies(query, page)
            .flatMap {
                saveMoviesCache(it)
            }
    }

    override fun getMovies(genre: Int, page: Int): Single<MoviesPagination> {
        return api.getMovies(genre, page)
            .flatMap {
                saveMoviesCache(it)
            }
    }

    private fun saveMoviesCache(movies: MoviesPagination): Single<MoviesPagination>? {
        return cache.saveMovies(movies.results).andThen(Single.just(movies))
    }
}

interface MovieDatasource {
    fun getMovies(genre: Int, page: Int): Single<MoviesPagination>
    fun searchMovies(query: String, page: Int): Single<MoviesPagination>
}
