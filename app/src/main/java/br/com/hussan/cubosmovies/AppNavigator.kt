package br.com.hussan.cubosmovies

import android.app.Activity
import android.os.Bundle
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.extensions.navigate
import br.com.hussan.cubosmovies.ui.moviedetails.MovieDetailsActivity

class AppNavigator(private val activity: Activity) {

    companion object {
        const val MOVIE = "MOVIE"
        const val EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME"
        const val EXTRA_TITLE_TRANSITION_NAME = "EXTRA_TITLE_TRANSITION_NAME"

    }

    fun gotoMovieDetails(movie: MovieView) {
        val bundle = Bundle().apply {
            putParcelable(MOVIE, movie)
        }
        activity.navigate<MovieDetailsActivity>(bundle)
    }
}
