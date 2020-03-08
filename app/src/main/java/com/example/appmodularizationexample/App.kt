package com.example.appmodularizationexample

import android.app.Application
import com.example.splash.di.splashModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val list = listOf(splashModule)
        startKoin {
            modules(list)
        }
    }
}