package com.example

import com.example.remote.R
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) : ResultWrapper<Nothing>()
}

private val TAG: String = "AppDebug"


suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ResultWrapper<T?> {
    return withContext(dispatcher) {
        try {
            val call = apiCall.invoke()
            ResultWrapper.Success(call)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ResultWrapper.GenericError(code)
                }
                is IOException -> {
                    ResultWrapper.GenericError(0)
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    ResultWrapper.GenericError(
                        null
                    )
                }
            }

        }
    }
}


//for custom error body
private fun convertErrorBody(throwable: HttpException): String? {
    try {
        val json = JSONTokener(throwable.response()?.errorBody()?.string()).nextValue();
        if (json is JSONObject || json is JSONArray) {
            val errorResponse = Gson().fromJson(json.toString(), String::class.java)
            errorResponse?.let { return it }
        }
        return ""

    } catch (exception: Exception) {
        return ""
    }
}