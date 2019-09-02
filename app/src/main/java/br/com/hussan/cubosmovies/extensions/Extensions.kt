package br.com.hussan.cubosmovies.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import br.com.hussan.cubosmovies.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    snack(resources.getString(messageRes), length)
}

fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG
) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

fun Float.spToPx(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this, context.resources.displayMetrics)

fun Float.pxToSp(context: Context) =
    this / context.resources.displayMetrics.scaledDensity

fun Disposable.add(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.scaleDown(callback: () -> Unit) {
    val animation = AnimationUtils.loadAnimation(context, R.anim.scale_down)
    animation.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                callback()
            }

            override fun onAnimationStart(p0: Animation?) {}
        }
    )
    startAnimation(animation)

}

fun View.scaleUp(callback: (() -> Unit?)? = null) {
    val animation = AnimationUtils.loadAnimation(context, R.anim.scale_up)
    animation.setAnimationListener(
        object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                callback?.invoke()
            }

            override fun onAnimationStart(p0: Animation?) {}
        }
    )
    startAnimation(animation)
}

inline fun <reified T : Activity> Activity.navigate(
    bundle: Bundle? = null,
    options: ActivityOptionsCompat? = null
) {
    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let { putExtras(bundle) }
        startActivity(this, options?.toBundle())
    }
}

inline fun <reified T : Activity> Activity.navigateForResult(
    codeRequest: Int,
    bundle: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let { putExtras(bundle) }
        startActivityForResult(this, codeRequest)
    }
}
