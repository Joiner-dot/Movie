package com.example.movierate.ui.adapters

import android.content.Intent
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.MoviesSeeNowRowBinding
import com.example.movierate.ui.activities.AboutMoviePage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

class SeeNowAdapter {

    fun seeNowMoviesDelegateAdapter() = adapterDelegate<Movie, Movie>(R.layout.movies_see_now_row) {
        val binding = MoviesSeeNowRowBinding.bind(itemView)

        bind{
            setIsRecyclable(false)
            binding.apply {
                imdbSeeNowRating.text = item.imdbRating

                rowRandom.setOnClickListener {
                    val intent = Intent(context, AboutMoviePage::class.java)
                    intent.putExtra("Movie", item)
                    context.startActivity(intent)
                }

                Picasso.get()
                    .load(item.poster)
                    .placeholder(R.drawable.background_about_movie)
                    .error(R.drawable.background_about_movie)
                    .into(moviePosterSeeNowMovie)
            }
        }
    }
}