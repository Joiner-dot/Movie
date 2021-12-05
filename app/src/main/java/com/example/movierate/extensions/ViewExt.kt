package com.example.movierate.extensions

import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.example.movierate.R

fun View.getColorByAttr(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data.takeIf { it != TypedValue.DATA_NULL_UNDEFINED }
        ?: ContextCompat.getColor(context, R.color.black)
}