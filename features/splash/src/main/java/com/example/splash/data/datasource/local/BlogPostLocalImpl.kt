package com.example.splash.data.datasource.local

import com.example.dao.BlogPostDao
import com.example.model.BlogPostApi

class BlogPostLocalImpl(val local: BlogPostDao) : BlogPostLocal {
    override suspend fun getBlogPosts(): List<BlogPostApi> {
        return local.getAllPosts()
    }

    override suspend fun insertPost(blog: BlogPostApi) {
        local.insert(blog)
    }
}