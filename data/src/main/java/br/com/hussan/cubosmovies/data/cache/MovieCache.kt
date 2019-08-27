package br.com.hussan.cubosmovies.data.cache

import br.com.hussan.cubosmovies.domain.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieCache {
    fun saveMovies(movies: List<Movie>): Completable
    fun getMovies(genre: Int): Single<List<Movie>>
}
