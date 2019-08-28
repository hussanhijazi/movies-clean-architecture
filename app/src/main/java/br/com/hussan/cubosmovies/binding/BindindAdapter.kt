package br.com.hussan.cubosmovies.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.hussan.cubosmovies.extensions.hide
import br.com.hussan.cubosmovies.views.ImageLoadingView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageLoadingView")
fun imageLoadingView(view: ImageLoadingView, image: String) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    Picasso.get().load("$baseUrl$image").into(
        view.image, object : Callback {
            override fun onSuccess() {
                view.progressBar.hide()
            }

            override fun onError(e: Exception?) {
                view.progressBar.hide()
            }
        }
    )
}

@BindingAdapter("bind:loadImage")
fun loadImage(view: ImageView, image: String) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    Picasso.get().load("$baseUrl$image").into(
        view
    )
}
