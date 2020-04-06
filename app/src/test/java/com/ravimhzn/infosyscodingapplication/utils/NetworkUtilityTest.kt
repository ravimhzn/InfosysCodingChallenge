package com.ravimhzn.infosyscodingapplication.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryRemoteDataSource
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Integration Test with real data. Make sure internet is connected.
 */
class NetworkUtilityTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var countryRemoteDataSource: CountryRemoteDataSource

    @Before
    fun init() {
        val apiService = NetworkUtility().service()
        countryRemoteDataSource = CountryRemoteDataSource(apiService)
    }

    @Test
    fun getCountryList() {
        val observableResult = countryRemoteDataSource.getCountryList()
        val result = observableResult.getOrAwaitValue(30, latchCount = 2)
        assertTrue(result is Result.Success<CountryInfo>)
    }
}