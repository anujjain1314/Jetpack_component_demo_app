package com.anujjain.androidFundamentals.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Defines methods for using the TrendingRepoDataModel
 * and perform operations related to it
 * on the [TrendingRepoDataBase].
 */
@Dao
interface TrendingRepoDAO {
    @Query("select * FROM trending_repo")
    fun getRepos() : LiveData<List<TrendingRepoDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepoList(repoList : List<TrendingRepoDataModel>)
}