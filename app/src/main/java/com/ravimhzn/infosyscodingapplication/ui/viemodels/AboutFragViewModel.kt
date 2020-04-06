package com.ravimhzn.infosyscodingapplication.ui.viemodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfoDataModel
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryDataSource
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import com.ravimhzn.infosyscodingapplication.utils.data.map
import javax.inject.Inject

class AboutFragViewModel @Inject constructor(
    private val countryDataSource: CountryDataSource
) : ViewModel() {

    private val _countryResult: MediatorLiveData<Result<CountryInfoDataModel>> = MediatorLiveData()
    val countryResult: LiveData<Result<CountryInfoDataModel>>
        get() = _countryResult

    init {
        getCountryData()
    }

    private fun getCountryData() {
        _countryResult.addSource(getCountryListFromServer())
        {
            it.map {
                _countryResult.value = Result.Success(it.toCountryInfoDataModel())
            }
        }
    }

    private fun getCountryListFromServer(): LiveData<Result<CountryInfo>> {
        return countryDataSource.getCountryList()
    }
}