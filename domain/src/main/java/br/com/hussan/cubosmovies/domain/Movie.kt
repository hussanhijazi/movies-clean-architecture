package br.com.hussan.cubosmovies.domain

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val originalTitle: String,
    val popularity: Double,
    val overview: String,
    val backdropPath: String?,
    val voteAverage: Double,
    val genreIds: List<Int>,
    val adult: Boolean? = null,
    val originalLanguage: String? = null,
    val releaseDate: String? = null,
    val video: Boolean? = null,
    val voteCount: Int? = null
)
