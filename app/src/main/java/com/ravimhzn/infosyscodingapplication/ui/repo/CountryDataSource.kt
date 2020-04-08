package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.model.Resource
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.utils.data.Result

/**
 * Implementation with LiveData and Datasource
 */
interface CountryDataSource {
    //fun getCountryList(): LiveData<Result<CountryInfo>>
    fun loadInformation(isrefresh: Boolean): LiveData<Resource<List<Row>>>
}