package br.com.hussan.cubosmovies.data.mapper

import br.com.hussan.cubosmovies.data.model.MoviesPaginationView
import br.com.hussan.cubosmovies.domain.MoviesPagination

class MoviesPaginationViewMapper(private val movieViewMapper: MovieViewMapper) :
    EntityViewMapper<MoviesPaginationView, MoviesPagination> {
    override fun mapToView(type: MoviesPagination): MoviesPaginationView {
        return MoviesPaginationView(
            type.page,
            type.results.map(movieViewMapper::mapToView),
            type.totalPages,
            type.totalResults
        )
    }
}
