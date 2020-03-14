package com.example.splash.data

import android.util.Log
import com.example.remote.NetworkBoundResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SplashRepository(val api: SplashServices) {
    private val TAG = "SplashRepositoryCall"
    fun getFoo(): Flow<List<BlogPost>> {
        getPosts()
        return flow {
            // exectute API call and map to UI object
            val fooList = api.getBlogPost()
            // Emit the list to the stream
            emit(fooList)
        }

        // Use the IO thread for this Flow
    }


    fun getPosts() {
        val newtWorkBound = object : NetworkBoundResource<List<BlogPost>, List<BlogPost>>(
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

            override suspend fun handleApiSuccessResponse(response: List<BlogPost>) {
                Log.e(TAG, response.size.toString())
            }

        }
    }
}