package com.example.home.di

import com.example.home.data.HomeRepository
import com.example.home.data.HomeRepositoryImpl
import com.example.home.data.HomeServices
import com.example.home.data.datasource.local.HomeLocal
import com.example.home.data.datasource.local.HomeLocalImpl
import com.example.home.data.datasource.remote.HomeRemote
import com.example.home.data.datasource.remote.HomeRemoteImpl
import com.example.home.domain.GetHomeUserCase
import com.example.home.presenter.HomeViewModel
import com.example.local.dao.ProductDao
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

    factory<HomeLocal> {
        HomeLocalImpl(get(),get())
    }


    factory<HomeRepository> {
        HomeRepositoryImpl(get(),get())
    }

    single {
        get<Retrofit>().create(HomeServices::class.java)
    }
}