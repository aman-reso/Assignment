package com.example.assignment.aman

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import java.util.concurrent.TimeUnit


object Utils {

   fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}