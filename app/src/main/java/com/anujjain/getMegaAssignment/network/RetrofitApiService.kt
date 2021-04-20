package com.anujjain.getMegaAssignment.network

import com.anujjain.getMegaAssignment.ApplicationSettings
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiService{

    fun create() : RetrofitApiInterface {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(ApplicationSettings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val logging = HttpLoggingInterceptor()
        logging.level = if(ApplicationSettings.isLoggingDisable) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY


        return retrofit.build().create(RetrofitApiInterface::class.java)
    }
}