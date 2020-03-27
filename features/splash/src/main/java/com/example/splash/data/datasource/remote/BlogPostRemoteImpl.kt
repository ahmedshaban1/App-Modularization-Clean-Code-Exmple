package com.example.splash.data.datasource.remote

import com.example.model.BlogPostApi
import com.example.splash.data.SplashServices

class BlogPostRemoteImpl(val api: SplashServices) : BlogPostRemote{
    override suspend fun getBlogPosts(): List<BlogPostApi> {
        return api.getBlogPost()
    }
}