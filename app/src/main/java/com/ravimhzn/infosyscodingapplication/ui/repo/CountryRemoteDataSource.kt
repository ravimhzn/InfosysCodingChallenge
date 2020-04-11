package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.utils.data.GeneralCallback
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : CountryDataSource {

    override fun getDataFromServerOrDB(): LiveData<Result<CountryInfo>> {
        val resultLiveData = MutableLiveData<Result<CountryInfo>>()
        resultLiveData.value = Result.Loading

        apiService.getCountryInfo().enqueue(
            GeneralCallback<CountryInfo, CountryInfo>(resultLiveData) {
                it
            }
        )
        return resultLiveData
    }
}