package com.ravimhzn.infosyscodingapplication.utils

import com.google.gson.GsonBuilder
import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkUtility {

    fun service() = retrofit().create(ApiService::class.java)

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("$BASE_URL")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()


    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }
}