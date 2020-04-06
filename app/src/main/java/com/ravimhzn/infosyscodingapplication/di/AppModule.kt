package com.ravimhzn.infosyscodingapplication.di

import android.app.Application
import android.content.Context
import com.ravimhzn.infosyscodingapplication.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun testString(): String {
        return "DI Working Correctly"
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //To support RxJava calls via Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}