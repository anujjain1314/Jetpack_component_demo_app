package com.anujjain.getMegaAssignment.trendingRepo.network

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiInterface {
    @GET("/repositories")
    suspend fun getTrendingRepos(): List<TrendingRepoResponseModel?>?
}