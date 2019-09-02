package br.com.hussan.cubosmoveis.cache

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hussan.cubosmovies.cache.AppDatabase
import br.com.hussan.cubosmovies.cache.MovieCacheImpl
import br.com.hussan.cubosmovies.cache.mapper.MovieEntityMapper
import br.com.hussan.cubosmovies.data.cache.MovieCache
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CacheMovieTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private val genre = 28
    private val query = "Matrix"

    private val entityMapper = MovieEntityMapper()

    private lateinit var movieCache: MovieCache

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        movieCache = MovieCacheImpl(appDatabase, entityMapper)
    }

    @Test
    fun saveMovies() {
        movieCache.saveMovies(MOVIES).test()
            .assertNoValues()
            .assertComplete()
    }

    @Test
    fun saveAndGetMoviesByName() {

        movieCache.saveMovies(MOVIES).test()
            .assertNoValues()
            .assertComplete()

        movieCache.getMoviesByName("Matrix").test()
            .assertValue(MOVIES)
            .assertComplete()
    }
}
