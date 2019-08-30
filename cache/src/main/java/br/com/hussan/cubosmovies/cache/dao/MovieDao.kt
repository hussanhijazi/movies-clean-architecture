package br.com.hussan.cubosmovies.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.hussan.cubosmovies.cache.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>): Completable

    // TODO
    @Query("SELECT * FROM movie WHERE genre_ids LIKE :genre")
    fun loadMovies(genre: String): Single<List<MovieEntity>>

}
