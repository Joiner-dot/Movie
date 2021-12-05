package com.example.movierate

import com.example.movierate.TestConstants.TITLE
import com.example.movierate.TestConstants.YEAR
import com.example.movierate.TestConstants.ACTORS
import com.example.movierate.TestConstants.DIRECTOR
import com.example.movierate.TestConstants.GENRE
import com.example.movierate.TestConstants.IMDB_ID
import com.example.movierate.TestConstants.PLOT
import com.example.movierate.TestConstants.WRITER
import com.example.movierate.api.objects.Movie
import com.example.movierate.api.objects.NetworkMovie
import com.example.movierate.mappers.FromNetworkMovieToMovie
import com.example.movierate.mappers.interfaces.Mapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.AssertionError

class MapperTest {

    private lateinit var mapper: Mapper<NetworkMovie, Movie>

    private lateinit var networkMovies: List<NetworkMovie>

    private lateinit var movies: List<Movie>

    @Before
    fun init() {
        mapper = FromNetworkMovieToMovie()
        networkMovies = listOf(
            NetworkMovie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            NetworkMovie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            NetworkMovie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )

        movies = listOf(
            Movie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                emptyList(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            Movie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                emptyList(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            Movie(
                TITLE,
                YEAR,
                GENRE,
                DIRECTOR,
                WRITER,
                ACTORS,
                PLOT,
                IMDB_ID,
                null,
                emptyList(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
    }

    @Test
    fun mapperTest() {
        Assert.assertEquals(movies, mapper.map(networkMovies))
    }

    @Test
    fun mapperTestOneMovie() {
        Assert.assertEquals(movies[0], mapper.map(networkMovies[0]))
    }

    @Test(expected = AssertionError::class)
    fun mapperTestException() {
        Assert.assertEquals(movies[1], mapper.map(networkMovies))
    }
}