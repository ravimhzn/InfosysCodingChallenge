package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import javax.inject.Inject

class CountryListRepository @Inject constructor(private val countryDataSource: CountryDataSource) :
    CountryDataSource {
    override fun getDataFromServer(): LiveData<Result<CountryInfo>> {
        return countryDataSource.getDataFromServer()
    }
}