package com.example.splash.domain

import com.example.model.BlogPost
import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import com.example.splash.data.SplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class GetBlogPostsUseCase(val repo: SplashRepository) {
    fun getPosts(): Flow<Resource<List<BlogPost>>> {
        return repo.getPosts().flatMapLatest { data -> map(data) }
    }

    suspend fun map(data: Resource<List<BlogPostApi>>): Flow<Resource<List<BlogPost>>> {
        return flow {
            val list = data.data?.map { BlogPost(it.pk, title = it.title) }
            emit(Resource(status = data.status, data = list, error = data.error))
        }
    }
}





