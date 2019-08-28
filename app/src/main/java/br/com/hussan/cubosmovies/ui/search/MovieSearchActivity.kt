package br.com.hussan.cubosmovies.ui.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.cubosmovies.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class MovieSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)
        setupToolbar()

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                setFragment(query)
            }
        }
    }

    private fun setFragment(query: String) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment,
            ListMoviesSearchFragment.newInstance(query)
        )
        fragmentTransaction.commit()
    }

    private fun setupToolbar() {
        setSupportActionBar(lytToolbar.toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}
