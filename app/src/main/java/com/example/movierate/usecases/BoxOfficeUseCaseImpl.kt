package com.example.movierate.usecases

import com.example.movierate.api.objects.Movie
import com.example.movierate.rest.RequestsRepository
import com.example.movierate.usecases.interfaces.BoxOfficeUseCase

class BoxOfficeUseCaseImpl(private val requestsRepository: RequestsRepository) : BoxOfficeUseCase {

    override suspend fun getBoxOfficeMovies(): List<Movie> {
        return requestsRepository.getBoxOfficeMovies()
    }
}