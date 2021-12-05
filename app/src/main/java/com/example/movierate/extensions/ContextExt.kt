package com.example.movierate.extensions

import android.content.Context
import android.content.res.Resources.Theme
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.movierate.R

@ColorInt
fun Context.getColorInt(@AttrRes attrRes: Int) =
    getResource(theme, attrRes).data.takeIf { it != TypedValue.DATA_NULL_UNDEFINED }
        ?: ContextCompat.getColor(this, R.color.black)


private fun getResource(theme: Theme, @AttrRes attrRes: Int): TypedValue {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue
}

@DrawableRes
fun Context.getDrawableRes(@AttrRes attrRes: Int) = getResource(theme, attrRes).data