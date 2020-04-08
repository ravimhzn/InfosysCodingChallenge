package com.ravimhzn.infosyscodingapplication.ui.repo

import androidx.lifecycle.LiveData
import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.model.Resource
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : CountryDataSource {


    override fun loadInformation(isrefresh: Boolean): LiveData<Resource<List<Row>>> {
        TODO()
    }


}