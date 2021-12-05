package com.example.movierate.api.objects.secondary

import com.google.gson.annotations.SerializedName

data class ListMoviesIdImdbApi(
    @SerializedName("movie_results")
    val movieResults:List<ImdbId>
    )
