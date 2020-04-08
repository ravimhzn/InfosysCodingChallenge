package com.ravimhzn.infosyscodingapplication.ui.viemodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ravimhzn.infosyscodingapplication.ui.model.Resource
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryDataSource
import com.ravimhzn.infosyscodingapplication.utils.data.AbsentLiveData
import javax.inject.Inject

class CountryListViewModel @Inject constructor(private val countryDataSource: CountryDataSource) :
    ViewModel() {

    lateinit var rowListLiveData: LiveData<Resource<List<Row>>>
    private var isRefreshingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        rowListLiveData = isRefreshingLiveData.switchMap {
            isRefreshingLiveData.value?.let { countryDataSource.loadInformation(it) }
                ?: AbsentLiveData.create()
        }
    }

    fun refreshCountryInformation(isrefresh: Boolean) = isRefreshingLiveData.postValue(isrefresh)

    fun getCountryInfoListValues() = rowListLiveData.value

}