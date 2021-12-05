package com.example.movierate.usecases.interfaces

import com.example.movierate.api.objects.Movie

interface SearchMovieUseCase {

    suspend fun getSearchMovies(movies: String): List<Movie>
}