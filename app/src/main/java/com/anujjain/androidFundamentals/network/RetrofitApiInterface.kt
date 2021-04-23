package com.anujjain.androidFundamentals.network

import com.anujjain.androidFundamentals.database.TrendingRepoDataModel
import retrofit2.http.GET

interface RetrofitApiInterface {
    @GET("/repositories")
    suspend fun getTrendingRepos(): List<TrendingRepoDataModel>
}