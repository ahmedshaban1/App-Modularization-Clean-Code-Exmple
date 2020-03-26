package com.example.di

import com.example.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().blogPostDao() }

}