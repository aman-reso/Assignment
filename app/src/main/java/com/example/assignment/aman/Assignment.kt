package com.example.assignment.aman

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Assignment : Application() {
    companion object {
        var INSTANCE: Assignment? = null
        fun getApplicationContext(): Assignment? {
            return INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (INSTANCE == null) {
            INSTANCE = applicationContext as Assignment?
        }
    }
}