package br.com.hussan.cubosmovies.cache.di

import androidx.room.Room.databaseBuilder
import br.com.hussan.cubosmovies.cache.AppDatabase
import br.com.hussan.cubosmovies.cache.MovieCacheImpl
import br.com.hussan.cubosmovies.cache.mapper.MovieEntityMapper
import br.com.hussan.cubosmovies.data.cache.MovieCache
import org.koin.dsl.module.module

val cacheModule = module {
    single {
        databaseBuilder(
            get(),
            AppDatabase::class.java, "cubosmovies"
        ).build()
    }
    single { MovieEntityMapper() }

    single<MovieCache> {
        MovieCacheImpl(get(), get())
    }
}
