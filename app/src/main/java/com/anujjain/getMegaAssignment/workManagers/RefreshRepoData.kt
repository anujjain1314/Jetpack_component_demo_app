package com.anujjain.getMegaAssignment.workManagers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anujjain.getMegaAssignment.database.TrendingRepoDataBase
import com.anujjain.getMegaAssignment.repository.TrendingRepoRepository
import retrofit2.HttpException

class RefreshRepoDataWorker(appContext: Context,params : WorkerParameters) : CoroutineWorker(appContext,params){
    companion object{
        const val WORK_NAME = "com.anujjain.getMegaAssignment.workManagers.RefreshRepoDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = TrendingRepoDataBase.getInstance(applicationContext)
        val repository = TrendingRepoRepository(database)

        try {
            repository.refreshRepoList()
            //Log.e("RefreshRepoDataWorker", "data sync done successfully")
        }catch (e: HttpException){
            Result.retry()
        }


        return Result.success()
    }


}