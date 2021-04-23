package com.anujjain.androidFundamentals.trendingRepo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * This class creates [TrendingRepoViewModel] with [app] as constructor parameter.
 */
class TrendingRepoViewModelFactory(val app : Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(TrendingRepoViewModel::class.java)){
            return TrendingRepoViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to create view model with given parameter")
    }

}