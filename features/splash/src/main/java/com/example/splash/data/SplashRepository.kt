package com.example.splash.data

import android.util.Log
import com.example.remote.NetworkBoundResource
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.Flow

class SplashRepository(val api: SplashServices) {
    private val TAG = "SplashRepositoryCall"

    fun getPosts(): Flow<Resource<List<BlogPost>>> {
        return object : NetworkBoundResource<List<BlogPost>>() {


            override suspend fun fetch(): List<BlogPost> {
                return api.getBlogPost()
            }

            override suspend fun saveFetchResult(data: List<BlogPost>) {
                Log.e(TAG, data.toString())
            }

        }.asFlow()
        /* val newtWorkBound = object : NetworkBoundResource<List<BlogPost>, List<BlogPost>>(
             true,
             true,
             true,
             false
         ) {
             override suspend fun createCacheRequestAndReturn() {
             }

             override fun setJob(job: Job) {
             }

             override suspend fun createCall(): List<BlogPost> {
                 return api.getBlogPost()
             }


             override suspend fun handleApiSuccessResponse(response: Resource<List<BlogPost>>) {
                 Log.e(TAG, response.toString())

             }

         }
         return newtWorkBound.build().asFlow()*/
    }
}