package com.example.auth.data.datasource.remote

import com.example.model.User
import com.google.gson.JsonObject

interface AuthRemote {
    suspend fun login(jsonObject: JsonObject) : User
    suspend fun register(jsonObject: JsonObject) :User
}