package com.example.home.di

import com.example.home.data.HomeRepository
import com.example.home.data.HomeRepositoryImpl
import com.example.home.data.HomeServices
import com.example.home.data.datasource.remote.HomeRemote
import com.example.home.data.datasource.remote.HomeRemoteImpl
import com.example.home.domain.GetHomeUserCase
import com.example.home.presenter.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    viewModel { HomeViewModel(get()) }

    factory {
        GetHomeUserCase(get())
    }


    factory<HomeRemote> {
        HomeRemoteImpl(get())
    }

    factory<HomeRepository> {
        HomeRepositoryImpl(get())
    }

    single {
        get<Retrofit>().create(HomeServices::class.java)
    }
}