package com.example.splash.data.datasource.remote

import com.example.model.BlogPostApi

interface BlogPostRemote {
    suspend fun getBlogPosts(): List<BlogPostApi>
}