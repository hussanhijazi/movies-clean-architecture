package br.com.hussan.cubosmovies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieView(
    val id: Int,
    val title: String,
    val posterPath: String
) : Parcelable
