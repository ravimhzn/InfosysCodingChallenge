package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val countryDataSource: CountryDataSource
) : CountryDataSource {
    override fun getCountryList(): LiveData<Result<CountryInfo>> {
        return countryDataSource.getCountryList()
    }
}