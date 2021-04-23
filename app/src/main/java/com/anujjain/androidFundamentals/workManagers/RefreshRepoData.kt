package com.anujjain.androidFundamentals.workManagers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anujjain.androidFundamentals.database.TrendingRepoDataBase
import com.anujjain.androidFundamentals.repository.TrendingRepoRepository
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