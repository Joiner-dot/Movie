package com.example.movierate.usecases.interfaces

import com.example.movierate.api.objects.Movie

interface BoxOfficeUseCase {

    suspend fun getBoxOfficeMovies(): List<Movie>
}