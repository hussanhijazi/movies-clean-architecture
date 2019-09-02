package br.com.hussan.cubosmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesPaginationView(
    val page: Int = 1,
    val results: List<MovieView> = listOf(),
    val totalPages: Int = 1,
    val totalResults: Int = 1
) : Parcelable
