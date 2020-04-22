package com.example.auth.presenter

import androidx.lifecycle.ViewModel
import com.example.auth.domain.LoginUserCase
import com.example.auth.domain.RegisterUseCase

class AuthViewModel(private val loginUseCase: LoginUserCase,private val registerUseCase: RegisterUseCase) : ViewModel() {
}