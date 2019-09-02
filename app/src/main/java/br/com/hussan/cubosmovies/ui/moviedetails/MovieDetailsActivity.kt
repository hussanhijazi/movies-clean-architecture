package br.com.hussan.cubosmovies.ui.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ActivityMovieDetailsBinding
import br.com.hussan.cubosmovies.domain.MovieVideos
import br.com.hussan.cubosmovies.extensions.add
import br.com.hussan.cubosmovies.extensions.scaleDown
import br.com.hussan.cubosmovies.extensions.scaleUp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailsBinding
    private val compositeDisposable = CompositeDisposable()
    private val imageAdapter by lazy {
        ImagesAdapter(this, lifecycle)
    }
    private val movie: MovieView
        get() = intent.getParcelableExtra(AppNavigator.MOVIE)


    companion object {
        fun newIntent(context: Context, movie: MovieView): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(AppNavigator.MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.movie = movie

        setupToolbar()
        setupViews()
        initImageSlider()
        setImages()
        getMovieVideos()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus)
            btnShare.scaleUp({})
    }

    private fun getMovieVideos() {
        viewModel.getMovieVideos(movie.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.results.isNotEmpty())
                    setVideos(it)
            }, {})
            .add(compositeDisposable)
    }

    private fun setImages() {
        movie.backdropPath?.let {
            imageAdapter.addItems(listOf(it))
        }
    }

    private fun initImageSlider() {
        vpPhotos.adapter = imageAdapter
    }

    private fun setVideos(videos: MovieVideos?) {
        imageAdapter.addItems(videos?.results ?: return)
    }

    private fun setupViews() {
        btnShare.run {
            setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${movie.title} - ${movie.overview}")
                    type = "text/plain"
                }
                startActivity(
                    Intent.createChooser(sendIntent, resources.getText(R.string.share_msg))
                )
            }
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToBack() {
        super.onBackPressed()
    }

    override fun onBackPressed() {
        btnShare.scaleDown {
            btnShare.visibility = View.GONE
            goToBack()
        }
    }
}
