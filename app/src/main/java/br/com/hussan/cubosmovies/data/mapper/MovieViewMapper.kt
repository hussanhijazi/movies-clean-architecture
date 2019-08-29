package br.com.hussan.cubosmovies.data.mapper

import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.domain.Movie

class MovieViewMapper : EntityViewMapper<MovieView, Movie> {
    override fun mapToView(type: Movie): MovieView {
        return MovieView(
            type.id,
            type.title,
            type.posterPath,
            type.originalTitle,
            type.popularity,
            type.overview,
            type.backdropPath,
            type.voteAverage
        )
    }
}
