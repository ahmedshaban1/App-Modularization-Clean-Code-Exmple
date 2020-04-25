package com.example.auth.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.auth.R
import com.example.auth.presenter.AuthViewModel
import com.example.common.BaseFragment
import com.example.common.showSnackbar
import com.example.remote.data.Resource
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailET
import kotlinx.android.synthetic.main.fragment_login.passwordEt
import kotlinx.android.synthetic.main.fragment_resgister.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login) {


    private val viewModel: AuthViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }


        viewModel.userLogin.observe(viewLifecycleOwner, Observer {
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
                    //navigate to dashboard
                    showSnackbar("your account logged in successfully")
                }
            }
        })

        submitLoginBtn.setOnClickListener {
            viewModel.login(
                emailET.text.toString(),
                passwordEt.text.toString()
            )
        }


    }

}
