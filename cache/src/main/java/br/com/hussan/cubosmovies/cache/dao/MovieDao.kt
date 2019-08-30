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

    @Query("SELECT * FROM movie WHERE genre_ids LIKE :genre")
    fun loadMoviesByGenre(genre: String): Single<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE name LIKE :query")
    fun loadMoviesByName(query: String): Single<List<MovieEntity>>
}
