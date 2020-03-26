package com.example.splash.di

import com.example.splash.data.SplashRepository
import com.example.splash.data.SplashServices
import com.example.splash.presenter.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val splashModule = module {
    viewModel { SplashViewModel(get()) }
    factory {
        SplashRepository(get(),get())
    }

    single {
        get<Retrofit>().create(SplashServices::class.java)
    }
}