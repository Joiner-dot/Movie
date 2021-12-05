package com.example.movierate.rest

import com.example.movierate.api.objects.Movie

interface RequestsRepository {

    suspend fun getMovieId(movie: String): List<Movie>

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getBoxOfficeMovies(): List<Movie>

    suspend fun getRandomMovies(): List<Movie>
}