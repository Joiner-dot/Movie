package com.example.movierate.usecases

import com.example.movierate.api.objects.Movie
import com.example.movierate.rest.RequestsRepository
import com.example.movierate.usecases.interfaces.SearchMovieUseCase

class SearchMovieUseCaseImpl(private val requestsRepository: RequestsRepository):SearchMovieUseCase {

    override suspend fun getSearchMovies(movies: String): List<Movie> {
        return requestsRepository.getMovieId(movies)
    }
}