package br.com.hussan.cubosmoveis.cache.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hussan.cubosmoveis.cache.MOVIE_ENTITY
import br.com.hussan.cubosmovies.cache.AppDatabase
import br.com.hussan.cubosmovies.cache.dao.MovieDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun saveMovies() {
        movieDao.insertAll(listOf(MOVIE_ENTITY)).test()
            .assertComplete()
            .assertNoValues()
    }

    @Test
    fun saveMovieAndGet() {

        movieDao.insertAll(listOf(MOVIE_ENTITY)).test()
            .assertComplete()
            .assertNoValues()

        movieDao.loadMoviesByName("Matrix").test()
            .assertValue(listOf(MOVIE_ENTITY))
    }
}

