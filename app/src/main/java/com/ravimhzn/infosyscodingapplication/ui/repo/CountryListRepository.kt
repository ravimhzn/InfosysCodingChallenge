package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.model.Resource
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import javax.inject.Inject

class CountryListRepository @Inject constructor() : CountryDataSource {
    override fun loadInformation(isrefresh: Boolean): LiveData<Resource<List<Row>>> {
        return loadInformation(isrefresh)
    }
}