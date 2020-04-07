package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.utils.data.Result

/**
 * Implementation with LiveData and Datasource
 */
interface CountryDataSource {
    fun getCountryList(): LiveData<Result<CountryInfo>>
}