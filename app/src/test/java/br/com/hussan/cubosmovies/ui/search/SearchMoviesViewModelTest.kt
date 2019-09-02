package br.com.hussan.cubosmovies.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.cubosmovies.data.mapper.MovieViewMapper
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.ui.MOVIES_PAGINATION
import br.com.hussan.cubosmovies.usecases.SearchMovies
import io.reactivex.Single
import mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class SearchMoviesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val searchMovies: SearchMovies = mock()
    private val mapper = MoviesPaginationViewMapper(MovieViewMapper())
    private lateinit var mViewModel: SearchMoviesViewModel
    private val language = "pt_BR"
    private val query = "Matrix"
    private val page = 1

    @Before
    fun setUp() {
        mViewModel = SearchMoviesViewModel(searchMovies, mapper, language)
    }

    @Test
    fun `Get movies by genre`() {

        `when`(
            searchMovies.invoke(
                query,
                page,
                language
            )
        ).thenReturn(Single.just(MOVIES_PAGINATION))

        mViewModel.searchMovies(query, page)
            .test()
            .assertValue(mapper.mapToView(MOVIES_PAGINATION))
            .assertComplete()

        verify(searchMovies).invoke(query, page, language)

    }

    @Test
    fun `Get movies by genre with error`() {

        val exception = Exception()

        `when`(searchMovies.invoke(query, page, language)).thenReturn(Single.error(exception))

        mViewModel.searchMovies(query, page)

        verify(searchMovies).invoke(query, page, language)
    }
}

