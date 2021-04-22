package com.anujjain.getMegaAssignment.network

import com.anujjain.getMegaAssignment.database.TrendingRepoDataModel
import retrofit2.http.GET

interface RetrofitApiInterface {
    @GET("/repositories")
    suspend fun getTrendingRepos(): List<TrendingRepoDataModel?>?
}