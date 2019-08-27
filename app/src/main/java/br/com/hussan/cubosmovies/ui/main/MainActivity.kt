package br.com.hussan.cubosmovies.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.hussan.cubosmovies.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val actionId = 28
        val dramaId = 18
        val fantasyId = 14
        val scienceFictionId = 878
    }

    private val fragments by lazy {
        listOf<Pair<String, Fragment>>(
            getString(R.string.action) to ListMoviesFragment.newInstance(actionId),
            getString(R.string.drama) to ListMoviesFragment.newInstance(dramaId),
            getString(R.string.fantasy) to ListMoviesFragment.newInstance(fantasyId),
            getString(R.string.science_fiction) to ListMoviesFragment.newInstance(scienceFictionId)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val adapter = TabAdapter(supportFragmentManager)
        adapter.add(fragments)
        viewPager.adapter = adapter
    }
}

