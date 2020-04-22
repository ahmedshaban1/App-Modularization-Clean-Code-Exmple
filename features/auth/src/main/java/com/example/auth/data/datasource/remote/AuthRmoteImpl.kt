package com.example.auth.data.datasource.remote

import com.example.auth.data.AuthServices
import com.example.model.User
import com.google.gson.JsonObject

class AuthRemoteImpl(private val authServices: AuthServices): AuthRemote {
    override suspend fun login(jsonObject: JsonObject): User {
        return authServices.login(jsonObject)
    }

    override suspend fun register(jsonObject: JsonObject): User {
        return authServices.register(jsonObject)
    }
}