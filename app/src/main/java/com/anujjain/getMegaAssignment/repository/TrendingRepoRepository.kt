package com.anujjain.getMegaAssignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.anujjain.getMegaAssignment.database.TrendingRepoDataBase
import com.anujjain.getMegaAssignment.database.TrendingRepoDataModel
import com.anujjain.getMegaAssignment.network.RetrofitApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingRepoRepository(private val dataBase: TrendingRepoDataBase) {

    val repoList : LiveData<List<TrendingRepoDataModel>> = dataBase.trendingRepoDAO.getRepos()

    suspend fun refreshRepoList(){
        withContext(Dispatchers.IO){
            Log.e("TrendingRepoRepository", "refresh repo list called")
            val repoList = RetrofitApiService.create().getTrendingRepos()
            dataBase.trendingRepoDAO.insertRepoList(repoList)

        }
    }

}