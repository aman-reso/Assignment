package com.example.assignment.aman.api

import com.example.assignment.aman.models.FriendsResponse
import retrofit2.http.GET


interface ApiInterface {

    @GET("/posts")
    suspend fun getFriendsList(): List<FriendsResponse>

}