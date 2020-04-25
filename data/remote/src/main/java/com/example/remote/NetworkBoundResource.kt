package com.example.remote

import com.example.ResultWrapper
import com.example.remote.data.Resource
import com.example.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart


abstract class NetworkBoundResource<T> {

    fun asFlow(): Flow<Resource<T>> = flow {

        if (shouldFetch()) {
            if (shouldFetchWithLocalData()) {
                emit(Resource.loading(data = localFetch()))
            }

            val results = safeApiCall(dispatcher = Dispatchers.IO) {
                remoteFetch()
            }

            when (results) {
                is ResultWrapper.Success -> {
                    results.value?.let {
                        saveFetchResult(results.value)
                    }
                    emit(Resource.success(data = results.value))
                }

                is ResultWrapper.GenericError -> {
                    emit(Resource.error(data = null, error = results.messageType))
                }
            }
        } else {
            //get from cash
            emit(Resource.success(data = localFetch()))
        }


    }.onStart {
        //get From cache
        emit(Resource.loading(data = null))
    }


    abstract suspend fun remoteFetch(): T
    abstract suspend fun saveFetchResult(data: T)
    abstract suspend fun localFetch(): T
    open fun onFetchFailed(throwable: Throwable) = Unit
    open fun shouldFetch() = true
    open fun shouldFetchWithLocalData() = false
}


