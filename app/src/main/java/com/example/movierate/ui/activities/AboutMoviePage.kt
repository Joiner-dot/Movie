package com.example.movierate.ui.activities

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.api.objects.secondary.Rating
import com.example.movierate.databinding.AboutMovieBinding
import com.example.movierate.extensions.getColorInt
import com.squareup.picasso.Picasso
import java.io.IOException
import java.lang.Appendable


class AboutMoviePage : AppCompatActivity() {

    private val binding: AboutMovieBinding by viewBinding()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val movie: Movie = intent.getSerializableExtra("Movie") as Movie
        supportActionBar?.apply {
            title = movie.title
            setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColorInt(R.attr.black)))
        window.statusBarColor = getColorInt(R.attr.black)
        binding.apply {
            poster.setImageResource(R.drawable.error)
            poster.scaleType = ImageView.ScaleType.FIT_CENTER
            imdbValue.text = movie.imdbRating
            dateOfRelease.text = movie.released
            director.text = "Director: ${movie.director}"
            actors.text = "Actors: ${movie.actors}"
            genre.text = "Genre: ${movie.genre}"
            plot.text = "Plot: ${movie.plot}"
            country.text = "Country: ${movie.country}"
            rated.text = "Rated: ${movie.rated}"
            runtime.text = "Runtime: ${movie.runtime}"
            awards.text = "Awards: ${movie.awards}"
            language.text = "Language: ${movie.language}"
            writer.text = "Writer: ${movie.writer}"
            boxOffice.text = "Fees: ${movie.boxOffice}"
            setRottenTomatoesValue(rottenTomatoesValue, movie.ratings)
            loadImage(movie.poster, poster)
        }
    }

    private fun setRottenTomatoesValue(rottenTomatoesValue: TextView, ratings: List<Rating>?) {
        ratings?.forEach { rating ->
            if (rating.source == "Rotten Tomatoes") {
                rottenTomatoesValue.text = rating.value
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadImage(url: String?, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.background_about_movie)
            .error(R.drawable.error)
            .into(imageView)
    }
}