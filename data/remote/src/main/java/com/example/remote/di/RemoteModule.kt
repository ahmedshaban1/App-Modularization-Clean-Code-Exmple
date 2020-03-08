package com.example.remote.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRemoteModule(baseUrl: String) = module {
    single {
        Retrofit.Builder().client(get()).baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.HEADERS
    }
}