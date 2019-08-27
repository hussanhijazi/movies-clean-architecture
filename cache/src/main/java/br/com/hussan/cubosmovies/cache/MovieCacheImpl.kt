package br.com.hussan.cubosmovies.cache

import br.com.hussan.cubosmovies.cache.mapper.MovieEntityMapper
import br.com.hussan.cubosmovies.data.cache.MovieCache
import br.com.hussan.cubosmovies.domain.Movie
import io.reactivex.Completable
import io.reactivex.Single

class MovieCacheImpl(
    private val db: AppDatabase,
    private val mapper: MovieEntityMapper
) : MovieCache {
    override fun getMovies(genre: Int): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
