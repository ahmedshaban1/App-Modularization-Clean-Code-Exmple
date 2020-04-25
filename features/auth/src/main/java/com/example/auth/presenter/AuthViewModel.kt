package com.example.auth.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.LoginUseCase
import com.example.auth.domain.RegisterUseCase
import com.example.model.User
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _userRegister = MutableLiveData<Resource<User>>()
    private val _userLogin = MutableLiveData<Resource<User>>()

    val userRegister: LiveData<Resource<User>> get() = _userRegister
    val userLogin: LiveData<Resource<User>> get() = _userLogin

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            registerUseCase.invoke(username, email, password).collect { data ->
                _userRegister.value = data
            }
        }

    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.invoke(email, password).collect { data ->
                _userLogin.value = data
            }
        }

    }
}