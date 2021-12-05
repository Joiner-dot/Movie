package com.example.movierate

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.movierate.extensions.getColorByAttr
import com.example.movierate.extensions.getColorInt
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtensionsTest {

    private lateinit var context: Context

    private lateinit var view: View

    @Before
    fun init() {
        context = ApplicationProvider.getApplicationContext()
        context.setTheme(R.style.Theme_MovieRate)
        view = View(context)
    }

    @Test
    fun testContextColorInt() {
        Assert.assertEquals(context.getColorInt(R.attr.colorPrimary), context.getColor(R.color.colorPrimary))
    }

    @Test
    fun testContextColorNull() {
        Assert.assertEquals(context.getColorInt(0), context.getColor(R.color.black))
    }

    @Test
    fun testViewColorInt() {
        Assert.assertEquals(view.getColorByAttr(R.attr.colorPrimary), context.getColor(R.color.colorPrimary))
    }

    @Test
    fun testViewColorNull() {
        Assert.assertEquals(view.getColorByAttr(0), context.getColor(R.color.black))
    }
}