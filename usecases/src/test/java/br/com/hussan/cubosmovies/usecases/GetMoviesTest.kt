package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetMoviesTest {

    private lateinit var getMovies: GetMovies
    private lateinit var repository: MovieDatasource
    private val genre = 28
    private val page = 1
    private val language = "pt_BR"

    @Before
    fun setUp() {
        repository = mock()
        getMovies = GetMovies(repository)
    }

    @Test
    fun `Get movies call`() {

        `when`(
            repository.getMovies(
                genre,
                page,
                language
            )
        ).thenReturn(Single.just(MOVIES_PAGINATION))

        getMovies.invoke(genre, page, language).test()
            .assertValue(MOVIES_PAGINATION)
            .assertNoErrors()
            .assertComplete()


        verify(repository).getMovies(genre, page, language)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get categories call with error`() {
        val exception = Exception()
        `when`(repository.getMovies(genre, page, language)).thenReturn(Single.error(exception))

        getMovies.invoke(genre, page, language).test().assertError(exception)

        verify(repository).getMovies(genre, page, language)
        verifyNoMoreInteractions(repository)
    }
}
