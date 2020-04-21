package com.example.common

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(),UiCommunicator {

    private lateinit var currentViewBinding: B
    private var progress: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentViewBinding = DataBindingUtil.setContentView(this, layoutId) as B
        onCreate(savedInstanceState, binding = currentViewBinding)
    }


    override fun showLoading() {
        progress?.dismiss() ?: kotlin.run {
            progress = ProgressDialog(this)
        }
        progress?.show()
    }

    override fun hideLoading() {
        progress?.dismiss()
    }

    override fun handleMessages(messageType: MessageType) {
        val message = ErrorMessageHelper.getMessage(messageType.code)
        when (messageType) {
            is MessageType.SnackBar -> {
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