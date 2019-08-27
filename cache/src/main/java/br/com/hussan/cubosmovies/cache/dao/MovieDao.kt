package br.com.hussan.cubosmovies.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.hussan.cubosmovies.cache.model.MovieEntity
import io.reactivex.Completable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<MovieEntity>): Completable
}
