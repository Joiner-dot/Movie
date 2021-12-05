package com.example.movierate.api.objects

import com.example.movierate.api.objects.secondary.Rating
import java.io.Serializable

data class Movie(
    val title: String?,
    val year: String?,
    val genre: String?,
    val director: String?,
    val writer: String?,
    val actors: String?,
    val plot: String?,
    val imdbID: String?,
    val poster: String?,
    val ratings: List<Rating>,
    val imdbRating: String?,
    val released:String?,
    val awards:String?,
    val runtime:String?,
    val country:String?,
    val language:String?,
    val rated:String?,
    val boxOffice:String?
) : Serializable
