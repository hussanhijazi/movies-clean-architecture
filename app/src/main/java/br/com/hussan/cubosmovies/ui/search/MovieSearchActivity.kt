package br.com.hussan.cubosmovies.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class MovieSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)
        setupToolbar()
        setFragment()
    }

    private fun setFragment() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment,
            ListMoviesSearchFragment.newInstance(intent.getStringExtra(AppNavigator.QUERY))
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
