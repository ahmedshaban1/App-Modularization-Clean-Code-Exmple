package com.example.appmodularizationexample

import android.app.Application
import com.example.auth.di.authModule
import com.example.di.localModule
import com.example.remote.di.getRemoteModule
import com.example.home.di.homeModule
import com.example.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val list = listOf(splashModule,getRemoteModule("http://192.168.1.3/blog/public/api/"), localModule,
            homeModule,authModule
        )
        startKoin {
            modules(list)
            androidContext(this@App)
        }
    }
}