package br.com.hussan.cubosmovies.data.di

import br.com.hussan.cubosmovies.data.datasource.*
import org.koin.dsl.module.module

val dataModule = module {
    single<MovieDatasource> { MovieRepository(get(), get()) }
}
