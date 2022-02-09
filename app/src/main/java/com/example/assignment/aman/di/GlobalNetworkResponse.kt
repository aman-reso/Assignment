package com.example.assignment.aman.di

sealed class GlobalNetworkResponse<out T> {
    data class Success<out T>(val value: T?): GlobalNetworkResponse<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): GlobalNetworkResponse<Nothing>()
    object NetworkError: GlobalNetworkResponse<Nothing>()
}
data class ErrorResponse(var success: Boolean? = null, var error: String? = null)