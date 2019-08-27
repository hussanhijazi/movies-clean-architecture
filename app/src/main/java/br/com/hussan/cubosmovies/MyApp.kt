package br.com.hussan.cubosmovies

import android.app.Application
import br.com.hussan.cubosmovies.cache.di.cacheModule
import br.com.hussan.cubosmovies.data.di.apiModule
import br.com.hussan.cubosmovies.data.di.dataModule
import br.com.hussan.cubosmovies.di.appModule
import br.com.hussan.cubosmovies.usecases.di.useCaseModule
import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, useCaseModule, apiModule, dataModule, cacheModule))
    }
}
