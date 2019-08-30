package br.com.hussan.cubosmovies.ui.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.binding.imageLoadingView
import br.com.hussan.cubosmovies.domain.Video
import br.com.hussan.cubosmovies.views.ImageLoadingView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class ImagesAdapter(
    private val mContext: Context,
    private val lifecycle: Lifecycle
) : PagerAdapter() {

    var imageList: MutableList<Any> = mutableListOf()

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    @Suppress("UnsafeCast")
    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val lytSlide: ViewGroup
        if (position == 0) {
            lytSlide =
                inflater.inflate(R.layout.lyt_slider, collection, false) as ViewGroup
            val imgPhoto = lytSlide.findViewById<ImageLoadingView>(R.id.imageSlider)
            imageLoadingView(imgPhoto, imageList[position] as String)

        } else {
            lytSlide =
                inflater.inflate(R.layout.lyt_slider_video, collection, false) as ViewGroup
            val videoMovie = lytSlide.findViewById<YouTubePlayerView>(R.id.videoMovieSlider)
            videoMovie.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val video = imageList[position] as Video
                    youTubePlayer.loadVideo(video.key, 0f)
                    youTubePlayer.pause()
                }
            })
            lifecycle.addObserver(videoMovie)
        }
        collection.addView(lytSlide)
        return lytSlide
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as? View)
    }

    override fun getCount() = imageList.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int) = imageList[position].toString()

    fun addItems(items: List<Any>) {
        imageList.addAll(items)
        notifyDataSetChanged()
    }
}
