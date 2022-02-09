package com.example.assignment.api

import com.example.assignment.aman.di.ErrorResponse
import com.example.assignment.aman.di.GlobalNetworkResponse

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): GlobalNetworkResponse<T> {
    return withContext(Dispatchers.IO) {
        try {
            GlobalNetworkResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> GlobalNetworkResponse.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    GlobalNetworkResponse.GenericError(code, errorResponse)
                }
                else -> {
                    GlobalNetworkResponse.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            return Gson().fromJson(it.charStream(), ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}

