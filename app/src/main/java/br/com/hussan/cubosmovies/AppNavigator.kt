package br.com.hussan.cubosmovies

import android.app.Activity
import android.os.Bundle
import br.com.hussan.cubosmovies.extensions.navigate
import br.com.hussan.cubosmovies.ui.search.SearchMovieActivity

class AppNavigator(private val activity: Activity) {

    companion object {
        const val QUERY = "QUERY"
    }

    fun navigateToSearch(query: String) {
        val bundle = Bundle().apply {
            putString(QUERY, query)
        }
        activity.navigate<SearchMovieActivity>(bundle)
    }
}
