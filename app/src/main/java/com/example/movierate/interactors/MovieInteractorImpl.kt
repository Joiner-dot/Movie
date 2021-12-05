package com.example.movierate.interactors

import com.example.movierate.interactors.interfaces.MovieInteractor
import com.example.movierate.usecases.interfaces.BoxOfficeUseCase
import com.example.movierate.usecases.interfaces.PopularMoviesUseCase
import com.example.movierate.usecases.interfaces.SearchMovieUseCase
import com.example.movierate.usecases.interfaces.SeeNowMovieUseCase

class MovieInteractorImpl(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val boxOfficeUseCase: BoxOfficeUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val seeNowMovieUseCase: SeeNowMovieUseCase
) : MovieInteractor {

    override suspend fun getPopularMovies() = popularMoviesUseCase.getPopularMovies()

    override suspend fun getBoxOfficeMovies() = boxOfficeUseCase.getBoxOfficeMovies()

    override suspend fun getSearchMovie(movie: String) = searchMovieUseCase.getSearchMovies(movie)

    override suspend fun getRandom() = seeNowMovieUseCase.getRandomMovies()
}