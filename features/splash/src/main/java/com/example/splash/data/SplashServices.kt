package com.example.splash.data

import com.example.model.BlogPostApi
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST


interface SplashServices {
    @GET("placeholder/blogs")
    suspend fun getBlogPost(): List<BlogPostApi>
}

