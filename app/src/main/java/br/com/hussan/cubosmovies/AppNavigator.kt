package br.com.hussan.cubosmovies

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.extensions.navigate
import br.com.hussan.cubosmovies.ui.moviedetails.MovieDetailsActivity

class AppNavigator(private val activity: Activity) {

    companion object {
        const val MOVIE = "MOVIE"
        const val EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME"
        const val EXTRA_TITLE_TRANSITION_NAME = "EXTRA_TITLE_TRANSITION_NAME"

    }

    fun gotoMovieDetails(movie: MovieView, imageView: ImageView, title: TextView) {
        val bundle = Bundle().apply {
            putParcelable(MOVIE, movie)
            putString(EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(imageView))
            putString(EXTRA_TITLE_TRANSITION_NAME, ViewCompat.getTransitionName(title))
        }

        var options: ActivityOptionsCompat? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransition = ViewCompat.getTransitionName(imageView)?.let {
                Pair.create<View, String>(imageView, it)
            }
            val titleTransition = ViewCompat.getTransitionName(title)?.let {
                Pair.create<View, String>(title, it)
            }

            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageTransition,
                titleTransition
            )
        }

        activity.navigate<MovieDetailsActivity>(bundle, options)

    }
}
