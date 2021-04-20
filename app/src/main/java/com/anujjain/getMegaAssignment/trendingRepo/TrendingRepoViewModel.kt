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

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val listResult = RetrofitApiService.create().getTrendingRepos()
                mData.value = listResult.size.toString()
            }catch (e: Exception){
                mData.value = e.message
            }
        }
    }
}