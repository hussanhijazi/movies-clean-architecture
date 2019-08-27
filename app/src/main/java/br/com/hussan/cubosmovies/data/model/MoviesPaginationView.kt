package br.com.hussan.cubosmovies.data.model

data class MoviesPaginationView(
    val page: Int,
    val results: List<MovieView>,
    val totalPages: Int,
    val totalResults: Int
)
