package com.anujjain.getMegaAssignment.trendingRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anujjain.getMegaAssignment.network.RetrofitApiService
import com.anujjain.getMegaAssignment.network.TrendingRepoDataModel
import kotlinx.coroutines.launch

import java.lang.Exception

class TrendingRepoViewModel : ViewModel() {

    private val _repoList = MutableLiveData<List<TrendingRepoDataModel?>>()
    val repoList : LiveData<List<TrendingRepoDataModel?>> get() = _repoList



    /**
     * Redirects to [NetworkErrorFragment] by setting this value to true when api call occurs.
     *
     * This is private because we don't want to expose setting this value outside.
     *
     * Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
     * with new values
     */
    private val mErrorFoundEvent = MutableLiveData<Boolean>()
    /**
     * observed in the [TrendingRepoFragment],
     * when value is [true], it navigates to [NetworkErrorFragment]
     *
     * The external LiveData interface to the property is immutable, so only this class can modify
     */
    val errorFoundEvent : LiveData<Boolean> get() = mErrorFoundEvent

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val listResult = RetrofitApiService.create().getTrendingRepos()
                _repoList.value = listResult
            }catch (e: Exception){
                _repoList.value = null
                mErrorFoundEvent.value = true
            }
        }
    }
}