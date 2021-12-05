package com.example.movierate.api.objects

import com.example.movierate.api.objects.secondary.Rating
import com.google.gson.annotations.SerializedName

data class NetworkMovie(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Writer")
    val writer: String?,
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("Poster")
    val poster: String?,
    @SerializedName("Ratings")
    val ratings: List<Rating>?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("Released")
    val released:String?,
    @SerializedName("Awards")
    val awards:String?,
    @SerializedName("Runtime")
    val runtime:String?,
    @SerializedName("Country")
    val country:String?,
    @SerializedName("Language")
    val language:String?,
    @SerializedName("Rated")
    val rated:String?,
    @SerializedName("BoxOffice")
    val boxOffice:String?
)
