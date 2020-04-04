package com.example.home.data

import com.example.home.data.datasource.local.HomeLocal
import com.example.home.data.datasource.remote.HomeRemote
import com.example.remote.NetworkBoundResource
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(val homeRemote: HomeRemote, val local: HomeLocal):HomeRepository {
    override suspend fun getHome(): Flow<Resource<List<HomeSection>>> {
       return object: NetworkBoundResource<List<HomeSection>>(){
           override suspend fun remoteFetch(): List<HomeSection> {
               return homeRemote.getHome()
           }
           override suspend fun saveFetchResult(data: List<HomeSection>) {
               local.saveHomeData(data)
           }
           override suspend fun localFetch(): List<HomeSection> {
              return local.getHome()
           }

           override fun shouldFetchWithLocalData(): Boolean {
               return true
           }
       }.asFlow()
    }
}