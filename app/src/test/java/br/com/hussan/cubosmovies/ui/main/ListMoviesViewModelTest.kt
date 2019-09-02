package br.com.hussan.cubosmovies.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.cubosmovies.data.mapper.MovieViewMapper
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.ui.MOVIES_PAGINATION
import br.com.hussan.cubosmovies.usecases.GetMovies
import io.reactivex.Single
import mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class ListMoviesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val getMovies: GetMovies = mock()
    private val mapper = MoviesPaginationViewMapper(MovieViewMapper())
    private lateinit var mViewModel: ListMoviesViewModel
    private val language = "pt_BR"
    private val genre = 28
    private val page = 1

    @Before
    fun setUp() {
        mViewModel = ListMoviesViewModel(getMovies, mapper, language)
    }

    @Test
    fun `Get movies by genre`() {

        `when`(getMovies.invoke(genre, page, language)).thenReturn(Single.just(MOVIES_PAGINATION))

        mViewModel.getMovies(genre, page)
            .test()
            .assertValue(mapper.mapToView(MOVIES_PAGINATION))
            .assertComplete()

        verify(getMovies).invoke(genre, page, language)

    }

    @Test
    fun `Get movies by genre with error`() {

        val exception = Exception()

        `when`(getMovies.invoke(genre, page, language)).thenReturn(Single.error(exception))

        mViewModel.getMovies(genre, page)

        verify(getMovies).invoke(genre, page, language)
    }
}

