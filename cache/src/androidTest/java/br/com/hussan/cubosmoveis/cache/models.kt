package br.com.hussan.cubosmoveis.cache

import br.com.hussan.cubosmovies.cache.model.MovieEntity
import br.com.hussan.cubosmovies.domain.Movie
import br.com.hussan.cubosmovies.domain.MovieVideos
import br.com.hussan.cubosmovies.domain.MoviesPagination
import br.com.hussan.cubosmovies.domain.Video
import java.util.*

val MOVIE = Movie(
    1, "Matrix", "poster_path", "Matrix",
    23.00, "", "", 12.00, listOf(28, 2, 3), Date()
)
val MOVIE_ENTITY = MovieEntity(
    1, "Matrix", "poster_path", "Matrix",
    23.00, "", "", 12.00, listOf(28, 2, 3), Date()
)

val MOVIES = listOf(MOVIE)
val MOVIES_PAGINATION = MoviesPagination(1, listOf(), 1, 1)


val MOVIE_VIDEOS = MovieVideos(
    1, listOf(
        Video(
            "123", "", "", "1231d", "Name",
            "", 1, "type"
        )
    )
)
