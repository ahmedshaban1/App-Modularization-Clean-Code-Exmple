package com.example.splash

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.common.BaseActivity
import com.example.splash.databinding.ActivitySplashBinding
import com.example.splash.presenter.SplashViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val TAG = "Splash Activity"
    val splashViewModel: SplashViewModel by viewModel()
    override fun onCreate(
        instance: Bundle?,
        binding: ActivitySplashBinding
    ) {
        binding.centerTextTv.text = "this is center center data from binding......"
        binding.centerTextTv.setOnClickListener {
            test()
        }


        splashViewModel.postsLD.observe(this, Observer {
            Log.d(TAG, it.size.toString())
        })


    }

    private fun test() {
        splashViewModel.getBlogPosts()
    }

    override val layoutId: Int
        get() = R.layout.activity_splash


}
