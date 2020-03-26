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
                    emit(Resource.error(data = null, error = Throwable()))
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

/*
abstract class NetworkBoundResource<ResponseObject, CacheObject>
    (
    val isNetworkAvailable: Boolean, // is their a network connection?
    val isNetworkRequest: Boolean, // is this a network request?
    val shouldCancelIfNoInternet: Boolean, // should this job be cancelled if there is no network?
    val shouldLoadFromCache: Boolean // should the cached data be loaded?
) {

    private val TAG: String = "AppDebug"
    protected lateinit var job: CompletableJob
    protected lateinit var coroutineScope: CoroutineScope
    private  var flow: Flow<Resource<ResponseObject>>? = null

    init {
        setJob(initNewJob())
    }

    fun build(): NetworkBoundResource<ResponseObject, CacheObject> {
        // setValue(DataState.loading(isLoading = true, cachedData = null))

        if (shouldLoadFromCache) {
            // view cache to start
            */
/*val dbSource = loadFromCache()
            result.addSource(dbSource){
                result.removeSource(dbSource)
                setValue(DataState.loading(isLoading = true, cachedData = it))
            }*//*

        }

        if (isNetworkRequest) {
            if (isNetworkAvailable) {
                doNetworkRequest()
            } else {
                if (shouldCancelIfNoInternet) {
                    onErrorReturn(
                        "CheckInterInteConntection",
                        shouldUseDialog = true,
                        shouldUseToast = false
                    )
                } else {
                    doCacheRequest()
                }
            }
        } else {
            doCacheRequest()
        }


        return this


    }

    fun doCacheRequest() {
        coroutineScope.launch {
            createCacheRequestAndReturn()
        }
    }



    fun doNetworkRequest() {
        coroutineScope.launch {

            flow = flow {
                emit(Resource.loading(data = null))
                // exectute API call and map to UI object
                val postList = createCall()
                // Emit the list to the stream
                emit(Resource.success(data = postList))
            }

            flow?.catch { case ->
                Log.d("sssss", case.message!!)
                emit(Resource.error(data = null, error = case))
            }?.collect { items ->
                handleApiSuccessResponse(items)
                onCompleteJob()

            }

            */
/*withContext(Dispatchers.Main) {

                // make network call
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)

                    coroutineScope.launch {
                        handleNetworkCall(response)
                    }
                }
            }*//*

        }

        GlobalScope.launch(Dispatchers.IO) {
            delay(15000)

            if (!job.isCompleted) {
                Log.e(TAG, "NetworkBoundResource: JOB NETWORK TIMEOUT.")
                //job.cancel(CancellationException(ErrorHandling.UNABLE_TO_RESOLVE_HOST))
            }
        }


    }


    */
/*suspend fun handleNetworkCall(response: GenericApiResponse<ResponseObject>){

      *//*
*/
/*  when(response){
            is ApiSuccessResponse ->{
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse ->{
                Log.e(TAG, "NetworkBoundResource: ${response.errorMessage}")
                onErrorReturn(response.errorMessage, true, false)
            }
            is ApiEmptyResponse ->{
                Log.e(TAG, "NetworkBoundResource: Request returned NOTHING (HTTP 204).")
                onErrorReturn("HTTP 204. Returned NOTHING.", true, false)
            }
        }*//*
*/
/*
    }*//*


    fun onCompleteJob(){
        GlobalScope.launch(Dispatchers.Main) {
            job.complete()
        }
    }

    fun onErrorReturn(errorMessage: String?, shouldUseDialog: Boolean, shouldUseToast: Boolean) {
        */
/*  var msg = errorMessage
          var useDialog = shouldUseDialog
          var responseType: ResponseType = ResponseType.None()
          if(msg == null){
              msg = ERROR_UNKNOWN
          }
          else if(ErrorHandling.isNetworkError(msg)){
              msg = ERROR_CHECK_NETWORK_CONNECTION
              useDialog = false
          }
          if(shouldUseToast){
              responseType = ResponseType.Toast()
          }
          if(useDialog){
              responseType = ResponseType.Dialog()
          }

          onCompleteJob(DataState.error(Response(msg, responseType)))*//*

    }

    */
/*  fun setValue(dataState: DataState<ViewStateType>){
          //result.value = dataState
      }*//*


    private fun initNewJob(): Job {
        Log.d(TAG, "initNewJob: called.")
        job = Job() // create new job
        job.invokeOnCompletion(
            onCancelling = true,
            invokeImmediately = true,
            handler = object : CompletionHandler {
                override fun invoke(cause: Throwable?) {
                    if (job.isCancelled) {
                        Log.e(TAG, "NetworkBoundResource: Job has been cancelled.")
                        cause?.let {
                            onErrorReturn(it.message, false, true)
                        } ?: onErrorReturn("Unknown error.", false, true)
                    } else if (job.isCompleted) {
                        Log.e(TAG, "NetworkBoundResource: Job has been completed.")
                        // Do nothing? Should be handled already
                    }
                }
            })
        coroutineScope = CoroutineScope(Dispatchers.IO + job)
        return job
    }


    abstract suspend fun createCacheRequestAndReturn()

    abstract suspend fun handleApiSuccessResponse(response: Resource<ResponseObject>)

    abstract suspend fun createCall(): ResponseObject

    fun asFlow(): Flow<Resource<ResponseObject>>? = flow

    //abstract fun loadFromCache(): LiveData<ViewStateType>

    //abstract suspend fun updateLocalDb(cacheObject: CacheObject?)

    abstract fun setJob(job: Job)

}
*/

