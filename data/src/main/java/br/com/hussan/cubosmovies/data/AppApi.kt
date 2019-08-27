package br.com.hussan.cubosmovies.data

import br.com.hussan.cubosmovies.domain.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("with_genres") genre: Int,
        @Query("page") page: Int
    ): Single<List<Movie>>
}
