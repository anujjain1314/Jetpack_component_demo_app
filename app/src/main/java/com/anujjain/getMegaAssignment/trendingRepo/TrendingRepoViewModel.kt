package com.anujjain.getMegaAssignment.trendingRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anujjain.getMegaAssignment.trendingRepo.network.RetrofitApiService
import kotlinx.coroutines.launch

import java.lang.Exception

class TrendingRepoViewModel : ViewModel() {

    private val mData = MutableLiveData<String>()
    val data : LiveData<String> get() = mData



    /**
     * Redirects to [NetworkErrorFragment] by setting this value to true when api call occurs.
     *
     * This is private because we don't want to expose setting this value outside.
     */
    private val mErrorFoundEvent = MutableLiveData<Boolean>()
    /**
     * observed in the [TrendingRepoFragment],
     * when value is [true], it navigates to [NetworkErrorFragment]
     */
    val errorFoundEvent : LiveData<Boolean> get() = mErrorFoundEvent

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val listResult = RetrofitApiService.create().getTrendingRepos()
                mData.value = listResult?.size.toString()
            }catch (e: Exception){
                mData.value = e.message
                mErrorFoundEvent.value = true
            }
        }
    }
}