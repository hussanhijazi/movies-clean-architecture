package br.com.hussan.cubosmovies.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import br.com.hussan.cubosmovies.R

class ImageLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val progressBar: ProgressBar
    val image: ImageView

    init {
        View.inflate(context, R.layout.view_image_loading, this)
        progressBar = findViewById(R.id.progressBar)
        image = findViewById(R.id.image)
    }
}
