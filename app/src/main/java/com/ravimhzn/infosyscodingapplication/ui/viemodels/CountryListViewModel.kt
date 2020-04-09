package com.ravimhzn.infosyscodingapplication.ui.viemodels

import android.content.Context
import android.view.View
import androidx.lifecycle.*
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

    init {
        println("debug -> Init Called")
        loadData()
        listenForGetCountry()
    }

    private fun listenForGetCountry() {
        getCountryInforResult.observeForever {
            it.map {
                println("debug -> TEST:: $it")
            }
        }
    }

    private fun loadData() {
        _countryListMediatorLiveData.addSource(getDataFromServer()) {
            it.map {
                _countryListMediatorLiveData.value = Result.Success(it)
            }
        }
    }

    private fun getDataFromServer(): LiveData<Result<CountryInfo>> {
        var a = countryListRepository.getDataFromServer().map {
            println(it.map {
                println("debug -> $it")
            })
        }
        return countryListRepository.getDataFromServer()
    }

    fun onGetDataFromServerSuccess(countryInfo: CountryInfo) {
        aboutRecyclerAdapter.setCountryInfo(countryInfo.rows as List<Row>)
        appBarTitle.value = countryInfo.title
    }
}