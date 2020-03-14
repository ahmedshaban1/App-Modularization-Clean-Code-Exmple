package com.example.splash.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST


interface SplashServices {
    @GET("placeholder/blogs")
    suspend fun getBlogPost(): List<BlogPost>
}

data class BlogPost(val pk:String,val title:String)