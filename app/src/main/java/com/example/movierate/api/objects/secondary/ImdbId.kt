package com.example.movierate.api.objects.secondary

import com.google.gson.annotations.SerializedName

data class ImdbId(
    @SerializedName("imdb_id")
    val imdbId: String
    )
