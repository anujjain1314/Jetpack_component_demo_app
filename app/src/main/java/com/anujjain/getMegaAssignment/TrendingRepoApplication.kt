package com.anujjain.getMegaAssignment

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.anujjain.getMegaAssignment.workManagers.RefreshRepoDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TrendingRepoApplication : Application(){

    private val applicationScope = CoroutineScope(Dispatchers.Default)


    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit(){
        applicationScope.launch {
            setUpRecurringWork()
        }
    }


    private fun setUpRecurringWork(){
        val repeatRequest = PeriodicWorkRequestBuilder<RefreshRepoDataWorker>(2,TimeUnit.HOURS).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshRepoDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatRequest)
    }


}