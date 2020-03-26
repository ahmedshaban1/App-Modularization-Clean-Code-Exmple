package com.example.splash.data

import android.util.Log
import com.example.dao.BlogPostDao
import com.example.model.BlogPost
import com.example.model.BlogPostApi
import com.example.remote.NetworkBoundResource
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

class SplashRepository(val api: SplashServices, val blogPostDao: BlogPostDao) {
    private val TAG = "SplashRepositoryCall"

    fun getPosts(): Flow<Resource<List<BlogPostApi>>> {
        return object : NetworkBoundResource<List<BlogPostApi>>() {
            override suspend fun saveFetchResult(data: List<BlogPostApi>) {
                if (data.isNotEmpty()) {
                    data.forEach { blog ->
                        val id = blogPostDao.insert(BlogPost(blog.pk, blog.title))
                        Log.d("SplashRepositoryCall", "Blog inserted : $id")
                    }

                }

            }

            override suspend fun remoteFetch(): List<BlogPostApi> {
                return api.getBlogPost()
            }

            override suspend fun localFetch(): List<BlogPostApi> {
                return listOf()
            }

        }.asFlow()

    }
}