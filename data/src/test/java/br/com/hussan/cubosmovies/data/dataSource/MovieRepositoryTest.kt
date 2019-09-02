package br.com.hussan.cubosmovies.data.dataSource

import br.com.hussan.cubosmovies.data.AppApi
import br.com.hussan.cubosmovies.data.MOVIES_PAGINATION
import br.com.hussan.cubosmovies.data.MOVIE_VIDEOS
import br.com.hussan.cubosmovies.data.cache.MovieCache
import br.com.hussan.cubosmovies.data.datasource.MovieDatasource
import br.com.hussan.cubosmovies.data.datasource.MovieRepository
import br.com.hussan.cubosmovies.data.mock
import com.google.gson.Gson
import io.reactivex.Completable
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    lateinit var repository: MovieDatasource
    lateinit var api: AppApi
    lateinit var mockServer: MockWebServer
    lateinit var gson: Gson
    lateinit var movieCache: MovieCache

    private val genre = 28
    private val query = "query"
    private val page = 1
    private val movieId = 123
    private val language = "pt_BR"


    @Before
    @Throws
    fun setUp() {

        gson = Gson()
        movieCache = mock()

        mockServer = MockWebServer()
        val baseURL = mockServer.url("/").toString()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(AppApi::class.java)

        repository = MovieRepository(api, movieCache)

    }

    @Test
    fun `Get movie remote by genre id and save locally`() {

        val movieResponse = MOVIES_PAGINATION
        val path = "/discover/movie?with_genres=$genre&page=$page&language=$language"

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(movieResponse))
        mockServer.enqueue(mockResponse)

        val moviesToSave = movieResponse.results

        `when`(movieCache.saveMovies(moviesToSave)).thenReturn(Completable.complete())

        repository.getMovies(genre, page, language).test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertValue(movieResponse)
            assertNoErrors()
            assertValueCount(1)
        }

        val request = mockServer.takeRequest()
        assertEquals(path, request.path)

        verify(movieCache).saveMovies(moviesToSave)

    }

    @Test
    fun `Get movie remote by query and save locally`() {

        val movieResponse = MOVIES_PAGINATION
        val path = "/search/movie?query=$query&page=$page&language=$language"

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(movieResponse))
        mockServer.enqueue(mockResponse)

        val moviesToSave = movieResponse.results

        `when`(movieCache.saveMovies(moviesToSave)).thenReturn(Completable.complete())

        repository.searchMovies(query, page, language).test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertValue(movieResponse)
            assertNoErrors()
            assertValueCount(1)
        }

        val request = mockServer.takeRequest()
        assertEquals(path, request.path)

        verify(movieCache).saveMovies(moviesToSave)

    }

    @Test
    fun `Get movie videos remote by movie id`() {

        val movieResponse = MOVIE_VIDEOS
        val path = "/movie/$movieId/videos?language=$language"

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(movieResponse))
        mockServer.enqueue(mockResponse)

        repository.getMovieVideos(movieId, language).test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertValue(movieResponse)
            assertNoErrors()
            assertValueCount(1)
        }

        val request = mockServer.takeRequest()
        assertEquals(path, request.path)

        verifyZeroInteractions(movieCache)
    }
}
