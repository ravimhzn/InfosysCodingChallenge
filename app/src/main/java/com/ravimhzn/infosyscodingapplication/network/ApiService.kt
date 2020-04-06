package com.ravimhzn.infosyscodingapplication.network

import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCountryInfo(): Call<CountryInfo>

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCountryPosts(): Observable<CountryInfo>
}