package com.example.auth.di

import com.example.auth.data.AuthServices
import com.example.auth.data.datasource.AuthRepository
import com.example.auth.data.datasource.local.AuthLocal
import com.example.auth.data.datasource.local.AuthLocalImpl
import com.example.auth.data.datasource.remote.AuthRemote
import com.example.auth.data.datasource.remote.AuthRemoteImpl
import com.example.auth.data.datasource.remote.AuthRepositoryImpl
import com.example.auth.domain.LoginUseCase
import com.example.auth.domain.RegisterUseCase
import com.example.auth.presenter.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val authModule = module {
    viewModel { AuthViewModel(get(), get()) }

    factory {
        LoginUseCase(get())
    }
    factory {
        RegisterUseCase(get())
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }


    factory<AuthRemote> {
        AuthRemoteImpl(get())
    }

    factory<AuthLocal> {
        AuthLocalImpl(get())
    }

    single {
        get<Retrofit>().create(AuthServices::class.java)
    }
}