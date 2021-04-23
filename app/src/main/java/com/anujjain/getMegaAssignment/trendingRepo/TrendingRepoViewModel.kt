package com.anujjain.getMegaAssignment.trendingRepo

import android.app.Application
import androidx.lifecycle.*
import com.anujjain.getMegaAssignment.database.TrendingRepoDataBase.Companion.getInstance
import com.anujjain.getMegaAssignment.database.TrendingRepoDataModel
import com.anujjain.getMegaAssignment.repository.TrendingRepoRepository
import kotlinx.coroutines.launch

import java.lang.Exception

class TrendingRepoViewModel(app: Application) : ViewModel() {

    /**
     * Responsible for providing data to [this] view model
     */
    private val dataRepository  = TrendingRepoRepository(dataBase = getInstance(app))

    // Internally, we use a MutableLiveData, because we will be updating the List with new values
    private val _repoList = dataRepository.repoList

    // The external LiveData interface to the property is immutable, so only this class can modify.
    val repoList : LiveData<List<TrendingRepoDataModel>> get() = _repoList

    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    /**
     * Event triggered for force refresh from user. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventForceRefresh = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for force refresh from user. Views should use this to get access
     * to the data.
     */
    val eventForceRefresh: LiveData<Boolean>
        get() = _eventForceRefresh

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    /**
     * Refresh the data in [TrendingRepoRepository]. Used coroutine launch to do the work in background
     */
    init {
        refreshRepoData()
    }


    private fun refreshRepoData(isForceRefresh : Boolean = false) {
        viewModelScope.launch {
            if(isForceRefresh){
                _eventForceRefresh.value = true
            }
            try {
                dataRepository.refreshRepoList()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = true

                forceRefreshDone()
            }catch (e: Exception){
                if(repoList.value.isNullOrEmpty()){
                    _eventNetworkError.value = true
                }

                forceRefreshDone()
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    /**
     * Resets the event [_eventForceRefresh] when data is refreshed forcefully .
     */
    fun forceRefreshDone() {
        _eventForceRefresh.value = false
    }


    fun onRefresh(){
        refreshRepoData(true)
    }
}