package br.com.hussan.cubosmovies.domain

data class MoviesPagination(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
