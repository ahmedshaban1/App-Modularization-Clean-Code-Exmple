package com.example.home.data

import retrofit2.http.GET

interface HomeServices {
    @GET("home")
    suspend fun getHome():List<HomeSection>
}