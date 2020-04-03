package com.example.home.domain

import com.example.home.data.HomeRepository
import com.example.home.data.HomeSection
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

class GetHomeUserCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): Flow<Resource<List<HomeSection>>> {
        return repository.getHome()
    }
}