package com.example

import com.example.common.MessageType
import com.example.common.MessageType.Dialog
import com.example.common.MessageType.SnackBar
import com.example.common.NetworkCodes
import com.example.common.NetworkCodes.GENERAL_ERROR
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
    data class GenericError(val messagType: MessageType) : ResultWrapper<Nothing>()
}





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
                    ResultWrapper.GenericError(messagType = SnackBar(NetworkCodes.TIMEOUT_ERROR))
                }
                is IOException -> {
                    ResultWrapper.GenericError(
                        SnackBar(NetworkCodes.CONNECTION_ERROR)
                    )
                }
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.GenericError(
                        SnackBar(code)
                    )
                }
                else -> {
                    ResultWrapper.GenericError(
                        Dialog(GENERAL_ERROR)
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

