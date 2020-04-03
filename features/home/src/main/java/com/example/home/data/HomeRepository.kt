package com.example.home.data

import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHome() : Flow<Resource<List<HomeSection>>>
}