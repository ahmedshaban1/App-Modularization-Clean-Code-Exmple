package com.example.auth.domain

import com.example.auth.data.datasource.AuthRepository
import com.example.model.User
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val  repository: AuthRepository) {
    suspend operator fun invoke(username:String,email:String,password:String): Flow<Resource<User>> {
        return repository.register(username,email,password)
    }
}