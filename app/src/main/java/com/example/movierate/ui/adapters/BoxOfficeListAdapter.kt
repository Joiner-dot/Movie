package com.example.movierate.ui.adapters

import android.content.Intent
import android.util.Log
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.BoxOfficeMovieRowBinding
import com.example.movierate.extensions.getColorByAttr
import com.example.movierate.ui.activities.AboutMoviePage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

class BoxOfficeListAdapter {

    fun boxOfficeDelegateAdapter() = adapterDelegate<Movie, Movie>(R.layout.box_office_movie_row) {

        val binding = BoxOfficeMovieRowBinding.bind(itemView)

        bind {
            binding.apply {
                setIsRecyclable(false)
                dateOfRelease.text = item.year
                movieName.text = item.title
                boxOffice.text = "Total: " + item.boxOffice
                when (adapterPosition) {
                    0 -> {
                        binding.row.setBackgroundResource(R.drawable.box_office_movie_first)
                        cup.setImageResource(R.drawable.gold_cup)
                        movieName.setTextColor(movieName.getColorByAttr(R.attr.black))
                        dateOfRelease.setTextColor(dateOfRelease.getColorByAttr(R.attr.black))
                        boxOffice.setTextColor(boxOffice.getColorByAttr(R.attr.black))
                    }
                    1 -> {
                        row.setBackgroundResource(R.drawable.box_office_movie_second)
                        cup.setImageResource(R.drawable.silver_cup)
                    }
                    2 -> {
                        row.setBackgroundResource(R.drawable.box_office_movie_third)
                        cup.setImageResource(R.drawable.bronze_cup)
                    }
                    else -> {
                        row.setBackgroundResource(R.drawable.box_office_movie_standart)
                    }
                }

                row.setOnClickListener {
                    Log.d("TEST", item.toString())
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