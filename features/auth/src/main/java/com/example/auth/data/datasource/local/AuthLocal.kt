package com.example.auth.data.datasource.local

import com.example.model.User

interface AuthLocal {
    suspend fun insertUser(user: User) : Long
    suspend fun deleteUser(user: User)
    suspend fun getUserUser() : Any
}