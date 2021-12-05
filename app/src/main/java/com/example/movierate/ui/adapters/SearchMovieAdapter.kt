package com.example.movierate.ui.adapters

import android.content.Intent
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.PopularMovieRowBinding
import com.example.movierate.ui.activities.AboutMoviePage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

class SearchMovieAdapter {

    fun searchMovieAdapter() = adapterDelegate<Movie, Movie>(R.layout.popular_movie_row) {

        val binding = PopularMovieRowBinding.bind(itemView)

        bind {
            binding.apply {
                valueIMDB.text = item.imdbRating
                movieName.text = item.title
                dateOfRelease.text = item.year
                item.ratings.forEach { element ->
                    if (element.source == "Rotten Tomatoes") {
                        otherSource.text = element.source
                        rottenTomatoesValue.text = element.value
                    }
                }
                row.setOnClickListener {
                    val intent = Intent(context, AboutMoviePage::class.java)
                    intent.putExtra("Movie", item)
                    context.startActivity(intent)
                }
                Picasso.get()
                    .load(item.poster)
                    .placeholder(R.drawable.background_about_movie)
                    .error(R.drawable.error)
                    .into(moviePoster)
            }

        }
    }
}