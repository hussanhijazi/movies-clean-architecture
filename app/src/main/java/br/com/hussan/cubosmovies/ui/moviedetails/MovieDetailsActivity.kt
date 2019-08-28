package br.com.hussan.cubosmovies.ui.moviedetails

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ActivityMovieDetailsBinding
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val movie: MovieView
        get() = intent.getParcelableExtra(AppNavigator.MOVIE)

    private val navigator: AppNavigator by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        setupToolbar()

        binding.movie = movie

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${movie.title} - ${movie.overview}"
                    )
                    type = "text/plain"
                }
                startActivity(
                    Intent.createChooser(
                        sendIntent,
                        resources.getText(R.string.share_msg)
                    )
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
