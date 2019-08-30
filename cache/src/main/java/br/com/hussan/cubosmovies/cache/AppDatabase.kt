package br.com.hussan.cubosmovies.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.hussan.cubosmovies.cache.dao.MovieDao
import br.com.hussan.cubosmovies.cache.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
