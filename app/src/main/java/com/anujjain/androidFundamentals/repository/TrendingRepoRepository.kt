package com.anujjain.androidFundamentals.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.anujjain.androidFundamentals.database.TrendingRepoDataBase
import com.anujjain.androidFundamentals.database.TrendingRepoDataModel
import com.anujjain.androidFundamentals.network.RetrofitApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This repository class is responsible for fetching Repo data from multiple data source
 * i.e from [TrendingRepoDataBase] or from network
 */

class TrendingRepoRepository(private val dataBase: TrendingRepoDataBase) {


    // provide the data stored in [TrendingRepoDataBase]
    val repoList : LiveData<List<TrendingRepoDataModel>> = dataBase.trendingRepoDAO.getRepos()


    /**
     * Forcefully fetch the data from network and
     * Refresh the data in [TrendingRepoDataBase]
     *
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     */
    suspend fun refreshRepoList(){
        withContext(Dispatchers.IO){
            Log.e("TrendingRepoRepository", "refresh repo list called")
            val repoList = RetrofitApiService.create().getTrendingRepos()
            dataBase.trendingRepoDAO.insertRepoList(repoList)

        }
    }

}