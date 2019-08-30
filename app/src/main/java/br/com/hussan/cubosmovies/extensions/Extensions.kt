package br.com.hussan.cubosmovies.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
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
    animate().scaleX(0F).scaleY(0F).setDuration(200)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                callback()
            }
        }).start()
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
