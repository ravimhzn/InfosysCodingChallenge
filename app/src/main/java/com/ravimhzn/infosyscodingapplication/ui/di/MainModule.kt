package com.ravimhzn.infosyscodingapplication.ui.di


import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.ui.adapter.AboutRecyclerAdapter
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryDataSource
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    /**
     * We don't have to declare @Singleton here since we are using scope binding.
     * It's already declared @Singleton in AppModule
     * This class references scoped bindings
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCountryDataSource(countryRemoteDataSource: CountryRemoteDataSource): CountryDataSource =
        countryRemoteDataSource

    @Provides
    fun provideRecyclerAdapter(): AboutRecyclerAdapter {
        return AboutRecyclerAdapter()
    }

}