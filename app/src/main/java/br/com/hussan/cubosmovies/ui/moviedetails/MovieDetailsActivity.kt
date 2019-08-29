package br.com.hussan.cubosmovies.ui.moviedetails

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.AppNavigator.Companion.EXTRA_IMAGE_TRANSITION_NAME
import br.com.hussan.cubosmovies.AppNavigator.Companion.EXTRA_TITLE_TRANSITION_NAME
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ActivityMovieDetailsBinding
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val movie: MovieView
        get() = intent.getParcelableExtra(AppNavigator.MOVIE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        setupToolbar()
        setupViews()
        setImageTransition()
        binding.movie = movie

    }

    private fun setImageTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent?.extras?.run {
                imgPoster.image.transitionName = getString(EXTRA_IMAGE_TRANSITION_NAME)
                txtTitle.transitionName = getString(EXTRA_TITLE_TRANSITION_NAME)
            }
        }
    }

    private fun setupViews() {
        btnShare.setOnClickListener {
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
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.details_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
