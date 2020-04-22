package com.example.auth.data

import com.example.model.User
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

    @POST("login")
    suspend fun login(@Body jsonObject: JsonObject): User

    @POST("register")
    suspend fun register(@Body jsonObject: JsonObject): User
}