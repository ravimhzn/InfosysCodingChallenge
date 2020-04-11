package com.ravimhzn.infosyscodingapplication.ui.viemodels

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.ui.adapter.AboutRecyclerAdapter
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfo
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.ui.repo.CountryListRepository
import com.ravimhzn.infosyscodingapplication.utils.data.Result
import com.ravimhzn.infosyscodingapplication.utils.data.map
import javax.inject.Inject


class CountryListViewModel @Inject constructor(
    private val countryListRepository: CountryListRepository,
    private val context: Context
) :
    ViewModel() {

    val aboutRecyclerAdapter: AboutRecyclerAdapter = AboutRecyclerAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val appBarTitle: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { }

    private val _countryListMediatorLiveData: MediatorLiveData<Result<CountryInfo>> =
        MediatorLiveData()
    val getCountryInforResult: LiveData<Result<CountryInfo>>
        get() = _countryListMediatorLiveData

    private val _connectionErrorMessage: MutableLiveData<Boolean> = MutableLiveData()
    val connectionErrorMessage: LiveData<Boolean>
        get() = _connectionErrorMessage

    private val isRefreshed = MutableLiveData<Boolean>()

    init {
        loadData()
        observeForever()
    }

    private fun observeForever() {
        getCountryInforResult.observeForever {
            it.map {
                onGetDataFromServerSuccess(it)
            }
        }
    }

    private fun loadData() {
        _countryListMediatorLiveData.addSource(countryListRepository.getDataFromServerOrDB()) {
            it.map {
                _countryListMediatorLiveData.value = Result.Success(it)
            }
        }
    }

    private fun onGetDataFromServerSuccess(countryInfo: CountryInfo) {
        aboutRecyclerAdapter.setCountryInfo(countryInfo.rows as List<Row>)
        appBarTitle.value = countryInfo.title
    }

    fun isRefresh(isRefreshed: Boolean) = apply { this.isRefreshed.value = isRefreshed }

    override fun onCleared() {
        super.onCleared()
        _countryListMediatorLiveData.removeSource(getCountryInforResult)
    }
}