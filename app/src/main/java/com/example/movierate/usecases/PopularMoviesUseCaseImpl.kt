package com.example.movierate.usecases

import com.example.movierate.api.objects.Movie
import com.example.movierate.rest.RequestsRepository
import com.example.movierate.usecases.interfaces.PopularMoviesUseCase

class PopularMoviesUseCaseImpl(private val requestsRepository: RequestsRepository):PopularMoviesUseCase {

   override suspend fun getPopularMovies(): List<Movie> {
        return requestsRepository.getPopularMovies()
    }
}