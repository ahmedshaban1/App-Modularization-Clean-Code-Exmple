package com.example.splash

import android.os.Bundle
import com.example.common.BaseActivity
import com.example.splash.presenter.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun getLayout() = R.layout.activity_splash
    override fun onCreate(instance: Bundle?, viewModel: SplashViewModel) {

    }


}
