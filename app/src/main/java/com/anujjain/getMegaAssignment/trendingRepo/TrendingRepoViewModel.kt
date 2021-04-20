package com.anujjain.getMegaAssignment.trendingRepo

import androidx.lifecycle.*
import com.anujjain.getMegaAssignment.ApiStatus
import com.anujjain.getMegaAssignment.network.RetrofitApiService
import com.anujjain.getMegaAssignment.network.TrendingRepoDataModel
import kotlinx.coroutines.launch

import java.lang.Exception

class TrendingRepoViewModel : ViewModel() {

    // Internally, we use a MutableLiveData, because we will be updating the List with new values
    private val _repoList = MutableLiveData<List<TrendingRepoDataModel?>>()

    // The external LiveData interface to the property is immutable, so only this class can modify.
    val repoList : LiveData<List<TrendingRepoDataModel?>> get() = _repoList

    // The internal MutableLiveData that stores the status of the most recent request
    private val _apiStatus = MutableLiveData<ApiStatus>()
    // The external immutable LiveData for the request status
    val apiStatus : LiveData<ApiStatus> = _apiStatus


    /**
     * Call getRepoData() on init so we can display status immediately.
     */
    init {
        getRepoData()
    }


    private fun getRepoData() {
        viewModelScope.launch {
            _apiStatus.value = ApiStatus.LOADING
            try {
                val listResult = RetrofitApiService.create().getTrendingRepos()
                _repoList.value = listResult
                _apiStatus.value = ApiStatus.DONE
            }catch (e: Exception){
                _repoList.value = null
                _apiStatus.value = ApiStatus.ERROR
            }
        }
    }
}