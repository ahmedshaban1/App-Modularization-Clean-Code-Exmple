package com.example.splash.data

import com.example.model.BlogPostApi
import com.example.remote.NetworkBoundResource
import com.example.remote.data.Resource
import com.example.splash.data.datasource.local.BlogPostLocal
import com.example.splash.data.datasource.remote.BlogPostRemote
import kotlinx.coroutines.flow.Flow

class SplashRepository(val remote: BlogPostRemote, val local: BlogPostLocal) {
    fun getPosts(): Flow<Resource<List<BlogPostApi>>> {
        return object : NetworkBoundResource<List<BlogPostApi>>() {
            override suspend fun saveFetchResult(data: List<BlogPostApi>) {
                if (data.isNotEmpty()) {
                    data.forEach { blog ->
                        local.insertPost(blog)
                    }
                }
            }

            override suspend fun remoteFetch(): List<BlogPostApi> {
                return remote.getBlogPosts()
            }

            override suspend fun localFetch(): List<BlogPostApi> {
                return local.getBlogPosts()
            }

            override fun shouldFetchWithLocalData() = true
        }.asFlow()

    }

}