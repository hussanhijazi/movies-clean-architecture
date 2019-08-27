package br.com.hussan.cubosmovies.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
    @GET("discover/movie")
    fun getMovies(@Query("with_genres") genre: Int): Observable<Any>
}
