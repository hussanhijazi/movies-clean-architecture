package br.com.hussan.cubosmovies.usecases.di

import br.com.hussan.cubosmovies.usecases.GetMovieVideos
import br.com.hussan.cubosmovies.usecases.GetMovies
import br.com.hussan.cubosmovies.usecases.SearchMovies
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetMovies(get()) }
    single { SearchMovies(get()) }
    single { GetMovieVideos(get()) }
}
