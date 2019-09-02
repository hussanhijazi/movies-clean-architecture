package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetSearchMoviesTest {

    private lateinit var searchMovies: SearchMovies
    private lateinit var repository: MovieDatasource
    private val query = "query"
    private val page = 1
    private val language = "pt_BR"

    @Before
    fun setUp() {
        repository = mock()
        searchMovies = SearchMovies(repository)
    }

    @Test
    fun `Search movies call`() {

        `when`(
            repository.searchMovies(
                query,
                page,
                language
            )
        ).thenReturn(Single.just(MOVIES_PAGINATION))

        searchMovies.invoke(query, page, language).test()
            .assertValue(MOVIES_PAGINATION)
            .assertNoErrors()
            .assertComplete()


        verify(repository).searchMovies(query, page, language)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get categories call with error`() {
        val exception = Exception()
        `when`(repository.searchMovies(query, page, language)).thenReturn(Single.error(exception))

        searchMovies.invoke(query, page, language).test().assertError(exception)

        verify(repository).searchMovies(query, page, language)
        verifyNoMoreInteractions(repository)
    }
}
