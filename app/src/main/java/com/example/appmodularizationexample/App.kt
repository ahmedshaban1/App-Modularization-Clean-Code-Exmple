package com.example.appmodularizationexample

import android.app.Application
import com.example.remote.di.getRemoteModule
import com.example.splash.di.splashModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val list = listOf(splashModule,getRemoteModule("https://open-api.xyz"))
        startKoin {
            modules(list)
        }
    }
}