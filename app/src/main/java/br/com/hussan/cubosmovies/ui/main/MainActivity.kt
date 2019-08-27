package br.com.hussan.cubosmovies.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity() {

    private val navigator: AppNavigator by inject { parametersOf(this) }

    companion object {
        const val actionId = 28
        const val dramaId = 18
        const val fantasyId = 14
        const val scienceFictionId = 878
    }

    private val fragments by lazy {
        listOf<Pair<String, Fragment>>(
            getString(R.string.action) to ListMoviesByGenreFragment.newInstance(actionId),
            getString(R.string.drama) to ListMoviesByGenreFragment.newInstance(dramaId),
            getString(R.string.fantasy) to ListMoviesByGenreFragment.newInstance(fantasyId),
            getString(R.string.science_fiction) to ListMoviesByGenreFragment.newInstance(
                scienceFictionId
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupTabLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                navigator.navigateToSearch(query ?: return false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(lytToolbar.toolbar)
    }

    private fun setupTabLayout() {
        val adapter = TabAdapter(supportFragmentManager)
        adapter.add(fragments)
        viewPager.adapter = adapter
    }
}
