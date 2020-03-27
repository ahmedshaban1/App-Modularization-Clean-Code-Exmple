package com.example.splash.di

import com.example.splash.data.SplashRepository
import com.example.splash.data.SplashServices
import com.example.splash.data.datasource.local.BlogPostLocal
import com.example.splash.data.datasource.local.BlogPostLocalImpl
import com.example.splash.data.datasource.remote.BlogPostRemote
import com.example.splash.data.datasource.remote.BlogPostRemoteImpl
import com.example.splash.domain.GetBlogPostsUseCase
import com.example.splash.presenter.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val splashModule = module {
    viewModel { SplashViewModel(get()) }
    factory {
        SplashRepository(get(), get())
    }

    factory {
        GetBlogPostsUseCase(get())
    }

    factory<BlogPostLocal> {
        BlogPostLocalImpl(get())
    }

    factory<BlogPostRemote> {
        BlogPostRemoteImpl(get())
    }


    single {
        get<Retrofit>().create(SplashServices::class.java)
    }
}