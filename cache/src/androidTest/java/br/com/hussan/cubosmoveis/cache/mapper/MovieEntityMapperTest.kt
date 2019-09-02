package br.com.hussan.cubosmoveis.cache.mapper

import br.com.hussan.cubosmoveis.cache.MOVIE
import br.com.hussan.cubosmoveis.cache.MOVIE_ENTITY
import br.com.hussan.cubosmovies.cache.mapper.MovieEntityMapper
import br.com.hussan.cubosmovies.cache.model.MovieEntity
import br.com.hussan.cubosmovies.domain.Movie
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieEntityMapperTest {

    private lateinit var movieEntityMapper: MovieEntityMapper

    @Before
    fun setUp() {
        movieEntityMapper = MovieEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val cachedMovie = movieEntityMapper.mapToCached(MOVIE)
        assertDataEquality(MOVIE, cachedMovie)
    }


    @Test
    fun mapFromCachedMapsData() {

        val movie = movieEntityMapper.mapFromCached(MOVIE_ENTITY)

        assertDataEquality(movie, MOVIE_ENTITY)
    }

    private fun assertDataEquality(
        category: Movie,
        cachedCategory: MovieEntity
    ) {
        assertEquals(category.id, cachedCategory.id)
    }
}
