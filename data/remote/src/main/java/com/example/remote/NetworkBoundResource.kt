package com.example.remote

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResponseObject, CacheObject>
    (
    isNetworkAvailable: Boolean, // is their a network connection?
    isNetworkRequest: Boolean, // is this a network request?
    shouldCancelIfNoInternet: Boolean, // should this job be cancelled if there is no network?
    shouldLoadFromCache: Boolean // should the cached data be loaded?
) {

    private val TAG: String = "AppDebug"
    protected lateinit var job: CompletableJob
    protected lateinit var coroutineScope: CoroutineScope

    init {
        setJob(initNewJob())
        // setValue(DataState.loading(isLoading = true, cachedData = null))

        if (shouldLoadFromCache) {
            // view cache to start
            /*val dbSource = loadFromCache()
            result.addSource(dbSource){
                result.removeSource(dbSource)
                setValue(DataState.loading(isLoading = true, cachedData = it))
            }*/
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
    }

    fun doCacheRequest() {
        coroutineScope.launch {
            createCacheRequestAndReturn()
        }
    }

    fun doNetworkRequest() {
        coroutineScope.launch {

            val flow = flow {
                // exectute API call and map to UI object
                val fooList = createCall()
                // Emit the list to the stream
                emit(fooList)
            }.catch { case ->
                Log.d("sssss", case.message!!)
            }.collect { items ->
                handleApiSuccessResponse(items)
            }

            /*withContext(Dispatchers.Main) {

                // make network call
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)

                    coroutineScope.launch {
                        handleNetworkCall(response)
                    }
                }
            }*/
        }

        GlobalScope.launch(Dispatchers.IO) {
            delay(15000)

            if (!job.isCompleted) {
                Log.e(TAG, "NetworkBoundResource: JOB NETWORK TIMEOUT.")
                //job.cancel(CancellationException(ErrorHandling.UNABLE_TO_RESOLVE_HOST))
            }
        }
    }

    /*suspend fun handleNetworkCall(response: GenericApiResponse<ResponseObject>){

      *//*  when(response){
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
    }*/

    /*fun onCompleteJob(dataState: DataState<ViewStateType>){
        GlobalScope.launch(Dispatchers.Main) {
            job.complete()
            setValue(dataState)
        }
    }*/

    fun onErrorReturn(errorMessage: String?, shouldUseDialog: Boolean, shouldUseToast: Boolean) {
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

          onCompleteJob(DataState.error(Response(msg, responseType)))*/
    }

    /*  fun setValue(dataState: DataState<ViewStateType>){
          //result.value = dataState
      }*/

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

    abstract suspend fun handleApiSuccessResponse(response: ResponseObject)

    abstract suspend fun createCall(): ResponseObject

    //abstract fun loadFromCache(): LiveData<ViewStateType>

    //abstract suspend fun updateLocalDb(cacheObject: CacheObject?)

    abstract fun setJob(job: Job)

}

