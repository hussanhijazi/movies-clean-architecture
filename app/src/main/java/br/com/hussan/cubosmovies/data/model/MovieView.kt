package br.com.hussan.cubosmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieView(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val originalTitle: String,
    val popularity: Double,
    val overview: String,
    val backdropPath: String?,
    val voteAverage: Double,
    val genreIds: List<Int>
) : Parcelable
