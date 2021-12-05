package com.example.movierate.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

fun AppCompatActivity.createToast(message: String = "Unable to get data") {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.listenError(errorListener: LiveData<String>) {
    errorListener.observe(this, { value ->
        createToast(value)
    })
}