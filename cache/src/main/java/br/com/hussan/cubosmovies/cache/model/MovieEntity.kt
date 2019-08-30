package br.com.hussan.cubosmovies.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.hussan.cubosmovies.cache.DateConverter
import br.com.hussan.cubosmovies.cache.GenreConverter
import java.util.*

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "poster_path") val postPath: String?,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "vote_avarage") val voteAverage: Double,
    @TypeConverters(GenreConverter::class)
    @ColumnInfo(name = "genre_ids") val genreIds: List<Int>,
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "release_date") val releaseDate: Date
)
