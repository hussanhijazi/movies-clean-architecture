package br.com.hussan.cubosmovies.domain

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val originalTitle: String,
    val popularity: Double,
    val overview: String,
    val backdropPath: String? = null,
    val adult: Boolean? = null,
    val genreIds: List<Int>? = null,
    val originalLanguage: String? = null,
    val releaseDate: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
