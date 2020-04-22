package com.example.auth.presenter.fragments


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.auth.R
import com.example.auth.presenter.AuthViewModel
import com.example.common.UiCommunicator
import com.example.common.showSnackbar
import com.example.remote.data.Resource
import kotlinx.android.synthetic.main.fragment_resgister.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(R.layout.fragment_resgister) {

    private val viewModel: AuthViewModel by viewModel()
    private var uiCommunicator: UiCommunicator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userRegister.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    uiCommunicator?.showLoading()
                }
                Resource.Status.ERROR -> {
                    uiCommunicator?.handleMessages(it.messageType!!)
                    uiCommunicator?.hideLoading()
                }
                Resource.Status.SUCCESS -> {
                    uiCommunicator?.hideLoading()
                    showSnackbar("sucesssssssssss")
                }
            }
        })

        submitRegisterBtn.setOnClickListener {
            viewModel.register(
                nameEt.text.toString(),
                emailET.text.toString(),
                passwordEt.text.toString()
            )
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UiCommunicator) {
            uiCommunicator = context
        } else {
            throw Exception("you must implement uiCommunicator listener ")
        }
    }

}
