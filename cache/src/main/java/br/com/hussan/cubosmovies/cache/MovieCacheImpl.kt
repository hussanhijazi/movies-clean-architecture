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
        return db.movieDao().loadMovies("%${genre}%").map { it.map { mapper.mapFromCached(it) } }
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return db.movieDao().insertAll(movies.map { mapper.mapToCached(it) })
    }
}
