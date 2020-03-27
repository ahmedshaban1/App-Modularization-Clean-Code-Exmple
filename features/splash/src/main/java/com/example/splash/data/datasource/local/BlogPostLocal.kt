package com.example.splash.data.datasource.local

import com.example.model.BlogPostApi

interface BlogPostLocal {
    suspend fun getBlogPosts(): List<BlogPostApi>

    suspend fun insertPost(blog:BlogPostApi)

}