package com.example.assignment.aman.viewmodels

import androidx.lifecycle.*
import com.example.assignment.aman.models.FriendsResponse
import com.example.assignment.aman.models.dummyString
import com.example.assignment.aman.repository.HomeRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(var repository: HomeRepository) : ViewModel() {
    var liveData = MutableLiveData<FriendsResponse?>(null)

    fun callApiRequest() {
        val gson=Gson()
        val response=   gson.fromJson(dummyString, FriendsResponse::class.java)
        liveData.postValue(response)
    }


    override fun onCleared() {
        super.onCleared()
    }
}

@Synchronized
fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> = MediatorLiveData<T>().also { mediator ->
    mediator.addSource(this, object : Observer<T> {
        private var isInitialized = false
        private var previousValue: T? = null

        override fun onChanged(newValue: T?) {
            val wasInitialized = isInitialized
            if (!isInitialized) {
                isInitialized = true
            }
            if(!wasInitialized || newValue != previousValue) {
                previousValue = newValue
                mediator.postValue(newValue)
            }
        }
    })
}
