package com.example.movierate.interactors.interfaces

import com.example.movierate.api.objects.Movie

interface MovieInteractor {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getBoxOfficeMovies(): List<Movie>

    suspend fun getSearchMovie(movie: String): List<Movie>

    suspend fun getRandom(): List<Movie>

}