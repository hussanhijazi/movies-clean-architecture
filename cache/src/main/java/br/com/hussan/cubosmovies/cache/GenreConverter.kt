package br.com.hussan.cubosmovies.cache

import androidx.room.TypeConverter

class GenreConverter {
    @TypeConverter
    fun fromListGenres(genres: List<Int>): String {
        return genres.joinToString(",")
    }

    @TypeConverter
    fun toListGenres(genres: String): List<Int> {
        if (genres.isNotEmpty())
            return genres.split(",").map {
                it.toInt()
            }
        return listOf()
    }
}


