package br.com.hussan.cubosmovies.data

import br.com.hussan.cubosmovies.domain.MoviesPagination
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("discover/movie")
    fun getMovies(@Query("with_genres") genre: Int, @Query("page") page: Int): Single<MoviesPagination>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Single<MoviesPagination>
}
