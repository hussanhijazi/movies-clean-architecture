package br.com.hussan.cubosmovies.binding

import androidx.databinding.BindingAdapter
import br.com.hussan.cubosmovies.extensions.hide
import br.com.hussan.cubosmovies.views.ImageLoadingView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import me.zhanghai.android.materialratingbar.MaterialRatingBar

@BindingAdapter("bind:imageLoadingView")
fun imageLoadingView(view: ImageLoadingView, image: String?) {
    image?.let {
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        Picasso.get().load("$baseUrl$it").into(
            view.image, object : Callback {
                override fun onSuccess() {
                    view.progressBar.hide()
                }

                override fun onError(e: Exception?) {
                    view.progressBar.hide()
                }
            }
        )
    } ?: view.progressBar.hide()
}

@BindingAdapter("bind:loadRating")
fun loadRating(view: MaterialRatingBar, rating: Double) {
    view.rating = rating.toFloat() / 2
}
