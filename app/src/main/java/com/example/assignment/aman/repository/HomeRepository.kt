package com.example.assignment.aman.repository

import com.example.assignment.aman.api.ApiInterface
import com.example.assignment.api.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository  @Inject constructor (var apiInterface: ApiInterface) {
    suspend fun getResponseFromServer() = safeApiCall {
         apiInterface.getFriendsList()
    }
}