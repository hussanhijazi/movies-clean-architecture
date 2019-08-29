package br.com.hussan.cubosmovies.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import br.com.hussan.cubosmovies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.view.*


class MainActivity : AppCompatActivity() {

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

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
        }

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
