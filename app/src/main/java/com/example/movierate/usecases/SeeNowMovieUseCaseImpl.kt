package com.example.movierate.usecases

import com.example.movierate.api.objects.Movie
import com.example.movierate.rest.RequestsRepository
import com.example.movierate.usecases.interfaces.SeeNowMovieUseCase

class SeeNowMovieUseCaseImpl(private val requestsRepository: RequestsRepository): SeeNowMovieUseCase {

    override suspend fun getRandomMovies(): List<Movie> {
        return requestsRepository.getRandomMovies()
    }
}