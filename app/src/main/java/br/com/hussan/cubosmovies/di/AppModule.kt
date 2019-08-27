package br.com.hussan.cubosmovies.di

import android.app.Activity
import br.com.hussan.cubosmovies.AppNavigator
import br.com.hussan.cubosmovies.data.mapper.MovieViewMapper
import br.com.hussan.cubosmovies.data.mapper.MoviesPaginationViewMapper
import br.com.hussan.cubosmovies.ui.main.ListMoviesViewModel
import br.com.hussan.cubosmovies.ui.search.SearchMoviesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { ListMoviesViewModel(get(), get()) }
    viewModel { SearchMoviesViewModel(get(), get()) }

    single { MovieViewMapper() }
    single { MoviesPaginationViewMapper(get()) }

    single { (activity: Activity) ->
        AppNavigator(activity = activity)
    }
}
