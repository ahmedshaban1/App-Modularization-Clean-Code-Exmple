package com.example.uicomponents.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(url: Any?) {
    url?.let {
        if (url is Int) {
            Picasso.get()
                .load(url)
                .into(this)

            setImageResource(url)
        }


        if (url is String)
            Picasso.get()
                .load(url)
                .into(this)

    }


}


fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

/**
 * Extension method to provide show keyboard for View.
 */
fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun ViewGroup.inflate(id: Int): View {
    return LayoutInflater.from(context).inflate(id, this, false)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun Context.getColorRes(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun Context.dp(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()

