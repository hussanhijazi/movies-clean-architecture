package br.com.hussan.cubosmovies.cache.mapper

import br.com.hussan.cache.cubosmovies.mapper.EntityMapper
import br.com.hussan.cubosmovies.cache.model.MovieEntity
import br.com.hussan.cubosmovies.domain.Movie

class MovieEntityMapper : EntityMapper<MovieEntity, Movie> {
    override fun mapFromCached(type: MovieEntity): Movie {
        return Movie(type.id, type.name, type.postPath)

    }

    override fun mapToCached(type: Movie): MovieEntity {
        return MovieEntity(type.id, type.title, type.posterPath)

    }

}
