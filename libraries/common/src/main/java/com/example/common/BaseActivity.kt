package com.example.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    private lateinit var currentViewBinding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentViewBinding = DataBindingUtil.setContentView(this, layoutId) as B
        onCreate(savedInstanceState, binding = currentViewBinding)
    }


    fun showLoading() {
        //todo impl loading feature

    }

    fun hideLoading() {
        //todo impl hide loading feature
    }

    fun handleMessages(messageType:MessageType){
        val message   =  ErrorMessageHelper.getMessage(messageType.code)
        when(messageType){
            is MessageType.SnackBar->{
                showSnackbar(message)
            }
        }

    }



    //all of this function needs future impl

    protected abstract fun onCreate(instance: Bundle?, binding: B)
    protected abstract val layoutId: Int

    protected fun getCurrentViewBinding(): B {
        return currentViewBinding
    }
}