package br.com.hussan.cubosmovies.data.di

import br.com.hussan.cubosmovies.data.AppApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "https://api.themoviedb.org/3/"
const val apiKey = "355905da2aa6783005496749a6ac5a53"

val apiModule = module {
    single {
        createWebService<AppApi>(get(), baseUrl)
    }
    factory {
        makeOkHttpClient(listOf(makeLoggingInterceptor(true), makeApiKeyInterceptor()))
    }
}

private fun makeOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder().apply {
        interceptors.forEach {
            this.addInterceptor(it)
        }
    }.connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

}

private fun makeApiKeyInterceptor(): Interceptor {
    return Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }
}

private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (isDebug)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return logging
}

private inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
            )
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
