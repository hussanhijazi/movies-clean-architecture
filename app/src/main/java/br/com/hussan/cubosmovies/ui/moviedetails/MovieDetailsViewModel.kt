package br.com.hussan.cubosmovies.ui.moviedetails

import androidx.lifecycle.ViewModel
import br.com.hussan.cubosmovies.domain.MovieVideos
import br.com.hussan.cubosmovies.usecases.GetMovieVideos
import io.reactivex.Single

class MovieDetailsViewModel(
    private val getMovieVideos: GetMovieVideos,
    private val language: String
) : ViewModel() {

    fun getMovieVideos(movieId: Int): Single<MovieVideos> {
        return getMovieVideos.invoke(movieId, language)
    }
}
