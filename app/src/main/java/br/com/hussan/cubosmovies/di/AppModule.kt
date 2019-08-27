package br.com.hussan.cubosmovies.di

import br.com.hussan.cubosmovies.data.mapper.MovieViewMapper
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.ui.main.ListMoviesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { ListMoviesViewModel(get(), get()) }

    single { MovieViewMapper() }
    single { MoviesPaginationViewMapper(get()) }

}
