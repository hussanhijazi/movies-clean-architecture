package br.com.hussan.cubosmovies.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "poster_path") val postPath: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "overview") val overview: String
)
