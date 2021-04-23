package com.anujjain.androidFundamentals.errorStates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkErrorViewModel : ViewModel() {

    private val _eventRetryAgain = MutableLiveData<Boolean>()
    val eventRetryAgain : LiveData<Boolean> get() = _eventRetryAgain

    fun onClickRetry(){
        _eventRetryAgain.value = true
    }

    fun onClickRetryDone(){
        _eventRetryAgain.value = false
    }

}