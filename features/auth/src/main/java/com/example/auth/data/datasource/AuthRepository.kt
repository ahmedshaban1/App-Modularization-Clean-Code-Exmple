package com.example.auth.data.datasource

import com.example.model.User
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email:String,password:String) : Flow<Resource<User>>
    suspend fun register(username:String,email:String,password:String) : Flow<Resource<User>>
}