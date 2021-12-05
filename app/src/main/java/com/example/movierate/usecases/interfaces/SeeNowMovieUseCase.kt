package com.example.movierate.usecases.interfaces

import com.example.movierate.api.objects.Movie

interface SeeNowMovieUseCase {

    suspend fun getRandomMovies(): List<Movie>
}