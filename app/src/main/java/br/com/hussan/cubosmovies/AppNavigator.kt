package br.com.hussan.cubosmovies

import android.app.Activity
import android.os.Bundle
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.extensions.navigate
import br.com.hussan.cubosmovies.ui.moviedetails.MovieDetailsActivity
import br.com.hussan.cubosmovies.ui.search.MovieSearchActivity

class AppNavigator(private val activity: Activity) {

    companion object {
        const val MOVIE = "MOVIE"
        const val QUERY = "QUERY"
    }

    fun goToSearch(query: String) {
        val bundle = Bundle().apply {
            putString(QUERY, query)
        }
        activity.navigate<MovieSearchActivity>(bundle)
    }

    fun gotoMovieDetails(movie: MovieView) {
        val bundle = Bundle().apply {
            putParcelable(MOVIE, movie)
        }

        activity.navigate<MovieDetailsActivity>()

    }
}
