package com.example.movierate.rest

import com.example.movierate.api.objects.secondary.ImdbId
import com.example.movierate.api.objects.Movie
import com.example.movierate.api.objects.NetworkMovie
import com.example.movierate.api.objects.secondary.SearchId
import com.example.movierate.mappers.FromNetworkMovieToMovie
import com.example.movierate.rest.api.MovieApi
import com.example.movierate.utils.RandomGenerator
import kotlinx.coroutines.*

private const val DEFAULT_START_PAGE = 1
private const val DEFAULT_END_PAGE = 4
private const val DEFAULT_START_YEAR = 1980
private const val DEFAULT_END_YEAR = 2021
private const val START_MOVIE_ID = "tt"

class RequestsRepositoryImpl(
    private val serviceRapidApi: MovieApi,
    private val serviceImdbApi: MovieApi,
    private val serviceAlternativeApi: MovieApi,
    private val movieMapper: FromNetworkMovieToMovie
) : RequestsRepository {

    override suspend fun getMovieId(movie: String): List<Movie> {
        return getMoviesFromRapidApi(serviceRapidApi.getMoviesByName(movie).results
            .filter { it.id.contains(START_MOVIE_ID) }
        )
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return getMoviesFromImdbApi(serviceImdbApi.getPopularMovies().toList()
            .filter { it.contains(START_MOVIE_ID) }
        )
    }

    override suspend fun getBoxOfficeMovies(): List<Movie> {
        return getMoviesFromAlternativeApi(
            serviceAlternativeApi.getBoxOfficeMovies().movieResults
        )
    }

    override suspend fun getRandomMovies(): List<Movie> {
        return getMoviesFromAlternativeApi(
            serviceAlternativeApi.getRandomMovies(
                RandomGenerator.generateRandomString(DEFAULT_START_PAGE, DEFAULT_END_PAGE),
                RandomGenerator.generateRandomString(DEFAULT_START_YEAR, DEFAULT_END_YEAR)
            ).movieResults
        )
    }

    private suspend fun getMoviesFromImdbApi(moviesId: List<String>): List<Movie> {
        var listMovies = emptyList<NetworkMovie>()
        withContext(Dispatchers.IO) {
            listMovies = moviesId.map {
                async {
                    serviceRapidApi.getMovie(substringOfId(it))
                }
            }
                .map { it.await() }
        }
        return movieMapper.map(listMovies.filter { it.title != null })
    }

    private suspend fun getMoviesFromRapidApi(moviesId: List<SearchId>): List<Movie> {
        var listMovies = emptyList<NetworkMovie>()
        withContext(Dispatchers.IO) {
            listMovies = moviesId.map {
                async {
                    serviceRapidApi.getMovie(substringOfId(it.id))
                }
            }
                .map { it.await() }
        }

        return movieMapper.map(listMovies.filter { it.title != null })
    }

    private suspend fun getMoviesFromAlternativeApi(moviesId: List<ImdbId>): List<Movie> {
        var listMovies = emptyList<NetworkMovie>()
        withContext(Dispatchers.IO) {
            listMovies = moviesId.map {
                async {
                    serviceRapidApi.getMovie(it.imdbId)
                }
            }
                .map { it.await() }
        }
        return movieMapper.map(listMovies.filter { it.title != null })
    }

    private fun substringOfId(line: String) = line.substring(
        line.indexOf(START_MOVIE_ID)
                until line.length - 1
    )
}
