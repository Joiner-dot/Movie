package com.example.movierate.usecases.interfaces

import com.example.movierate.api.objects.Movie

interface PopularMoviesUseCase {

    suspend fun getPopularMovies(): List<Movie>
}