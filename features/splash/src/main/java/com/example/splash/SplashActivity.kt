package com.example.splash

import android.os.Bundle
import com.example.common.BaseActivity
import com.example.splash.databinding.ActivitySplashBinding
import com.example.splash.presenter.SplashViewModel
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    val viewModel: SplashViewModel by inject()
    override fun onCreate(
        instance: Bundle?,
        binding: ActivitySplashBinding
    ) {
        binding.centerTextTv.text = "this is center center data from binding......"
        binding.centerTextTv.setOnClickListener {
            test()
        }

    }

    private fun test() {
        getCurrentViewBinding().centerTextTv.text = viewModel.getString()
    }

    override val layoutId: Int
        get() = R.layout.activity_splash


}
