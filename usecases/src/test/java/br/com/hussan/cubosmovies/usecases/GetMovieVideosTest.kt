package br.com.hussan.cubosmovies.usecases

import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetMovieVideosTest {

    private lateinit var getMovieVideos: GetMovieVideos
    private lateinit var repository: MovieDatasource
    private val movieId = 123
    private val language = "pt_BR"

    @Before
    fun setUp() {
        repository = mock()
        getMovieVideos = GetMovieVideos(repository)
    }

    @Test
    fun `Get movie videos`() {

        `when`(
            repository.getMovieVideos(movieId, language)
        ).thenReturn(Single.just(MOVIE_VIDEOS))

        getMovieVideos.invoke(movieId, language).test()
            .assertValue(MOVIE_VIDEOS)
            .assertNoErrors()
            .assertComplete()


        verify(repository).getMovieVideos(movieId, language)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get categories call locally with error`() {
        val exception = Exception()
        `when`(repository.getMovieVideos(movieId, language)).thenReturn(Single.error(exception))

        getMovieVideos.invoke(movieId, language).test().assertError(exception)

        verify(repository).getMovieVideos(movieId, language)
        verifyNoMoreInteractions(repository)
    }
}
