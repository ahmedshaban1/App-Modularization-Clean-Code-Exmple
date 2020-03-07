package com.example.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//todo adding viewmodel as genaric type t
// adding databinding  as generic Type b
abstract class BaseActivity<M : ViewModel, B : ViewDataBinding> : AppCompatActivity() {
    private lateinit var viewModelObject: M
    private lateinit var currentViewBinding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentViewBinding = DataBindingUtil.setContentView(this, layoutId) as B
        viewModelObject =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(viewModel)
        onCreate(savedInstanceState, viewModel = viewModelObject, binding = currentViewBinding)
    }


    fun showLoading() {
        //todo impl loading feature

    }

    fun hideLoading() {
        //todo impl hide loading feature
    }

    //all of this function needs future impl

    protected abstract fun onCreate(instance: Bundle?, viewModel: M, binding: B)
    protected abstract val layoutId: Int
    protected abstract val viewModel: Class<M>
    protected fun getCurrentViewModel(): M {
        return viewModelObject
    }

    protected fun getCurrentViewBinding(): B {
        return currentViewBinding
    }
}