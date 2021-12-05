package com.example.movierate.mappers

import com.example.movierate.api.objects.Movie
import com.example.movierate.api.objects.NetworkMovie
import com.example.movierate.mappers.interfaces.Mapper

class FromNetworkMovieToMovie : Mapper<NetworkMovie, Movie> {
    override fun map(from: NetworkMovie) =
        Movie(
            from.title,
            from.year,
            from.genre,
            from.director,
            from.writer,
            from.actors,
            from.plot,
            from.imdbID,
            from.poster,
            from.ratings.takeIf { it != null } ?: emptyList(),
            from.imdbRating,
            from.released,
            from.awards,
            from.runtime,
            from.country,
            from.language,
            from.rated,
            from.boxOffice
        )
}