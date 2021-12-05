package com.example.movierate.rest.api

import com.example.movierate.api.objects.NetworkMovie
import com.example.movierate.api.objects.secondary.HttpConstants.API_HOST_ALTERNATIVE
import com.example.movierate.api.objects.secondary.HttpConstants.API_HOST_IMDB
import com.example.movierate.api.objects.secondary.HttpConstants.API_HOST_MOVIES
import com.example.movierate.api.objects.secondary.ListMoviesId
import com.example.movierate.api.objects.secondary.ListMoviesIdImdbApi
import com.example.movierate.utils.RandomGenerator
import retrofit2.http.*
import kotlin.random.Random


interface MovieApi {

    @Headers(API_HOST_ALTERNATIVE)
    @GET("?r=json")
    suspend fun getMovie(@Query("i") id: String): NetworkMovie


    @Headers(API_HOST_IMDB)
    @GET("title/find")
    suspend fun getMoviesByName(@Query("q") name: String): ListMoviesId

    @Headers(API_HOST_MOVIES)
    @GET("?type=get-boxoffice-movies")
    suspend fun getBoxOfficeMovies(@Query("page") page: String = "1"): ListMoviesIdImdbApi

    @Headers(API_HOST_MOVIES)
    @GET("?type=get-popular-movies")
    suspend fun getRandomMovies(
        @Query("page") page: String,
        @Query("year") year: String
    ): ListMoviesIdImdbApi

    @Headers(API_HOST_IMDB)
    @GET("get-most-popular-movies")
    suspend fun getPopularMovies(): Array<String>
}