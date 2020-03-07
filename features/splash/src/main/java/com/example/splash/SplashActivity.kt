package com.example.splash

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.common.BaseActivity
import com.example.splash.databinding.ActivitySplashBinding
import com.example.splash.presenter.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel,ActivitySplashBinding>() {


    override fun onCreate(
        instance: Bundle?,
        viewModel: SplashViewModel,
        binding: ActivitySplashBinding
    ) {
        binding.centerTextTv.text = "this is center center data from binding......"
        binding.centerTextTv.setOnClickListener {
            test()
        }

    }

    fun test(){
        getCurrentViewBinding().centerTextTv.text = getCurrentViewModel().getString()
    }

    override val layoutId: Int
        get() = R.layout.activity_splash

    override val viewModel: Class<SplashViewModel>
        get() = SplashViewModel::class.java




}
