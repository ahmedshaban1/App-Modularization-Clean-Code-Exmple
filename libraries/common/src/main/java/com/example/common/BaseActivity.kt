package com.example.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

//todo adding viewmodel as genaric type t
// adding databinding  as generic Type b
abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun showLoading() {
        //todo impl loading feature

    }

    fun hideLoading() {
        //todo impl hide loading feature
    }

    //all of this function needs future impl
    protected abstract fun getLayout(): Int

    protected abstract fun onCreate(instance: Bundle?, viewModel: T)


}