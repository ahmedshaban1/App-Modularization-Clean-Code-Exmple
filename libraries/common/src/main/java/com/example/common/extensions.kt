package com.example.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.showSnackbar(snackbarText: String, timeLength: Int = 2000) {
    Snackbar.make(findViewById<View>(android.R.id.content), snackbarText, timeLength).show()
}

fun Fragment.showSnackbar(snackbarText: String, timeLength: Int = 2000) {
    (activity as AppCompatActivity).showSnackbar(snackbarText, timeLength)
}