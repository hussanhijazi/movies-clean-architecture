package br.com.hussan.cubosmovies.usecases.di

import br.com.hussan.cubosmovies.usecases.*
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetMovies(get()) }
}
