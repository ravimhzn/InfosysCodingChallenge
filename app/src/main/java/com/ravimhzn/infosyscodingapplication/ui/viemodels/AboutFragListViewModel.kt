package com.ravimhzn.infosyscodingapplication.ui.viemodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.persistence.CountryInfoDao
import com.ravimhzn.infosyscodingapplication.ui.adapter.AboutRecyclerAdapter
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.utils.NetworkUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AboutFragListViewModel @Inject constructor(
    private val countryInfoDao: CountryInfoDao,
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil
) :
    ViewModel() {

    val aboutRecyclerAdapter: AboutRecyclerAdapter = AboutRecyclerAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val appBarTitle: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { }
    private lateinit var subscription: Disposable

    init {
        if (!isInternetConnected()) {
            errorMessage.value = R.string.no_connection
        }
        loadData()
    }

    /**
     * If there is no internet connection, this function will load data from local database
     * otherwise it will load data from api service
     */
    private fun loadData() {
        subscription = Observable.fromCallable {
            countryInfoDao.getCountryDetailsFromDb
        }.concatMap { dbCountryInfoList ->
            if (!isInternetConnected())
                Observable.just(dbCountryInfoList)
            else
                getInfoListFromServer()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCountryListStart() }
            .doOnTerminate { onRetrieveCountryListFinish() }
            .subscribe(
                { result -> onRetrieveCounryListSuccess(result as List<Row>) },
                { onRetrievePostListError() }
            )
    }


    fun getInfoListFromServer(): Observable<List<Row?>>? {
        return apiService.getCountryPosts().map {
            it.rows?.filter { it?.let { it1 -> checkIfValuesNotNull(it1) }!! }
        }.concatMap { apiList ->
            countryInfoDao.deleteAll()
            countryInfoDao.insertIntoDbCountryDetails(*apiList.toTypedArray())
            Observable.just(apiList)
        }
    }

    /**
     * Filtering list. Checking if there are null
     */
    fun checkIfValuesNotNull(it: Row): Boolean {
        return it?.title != null && it?.description != null && it?.imageHref != null
    }

    private fun onRetrieveCountryListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCountryListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCounryListSuccess(postList: List<Row>) {
        aboutRecyclerAdapter.setCountryInfo(postList)
        appBarTitle.value = "About Canada"
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.unknown_error_message
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /**
     * Check if the device has an internet connection.
     *
     * @return true if device is connected to internet. Otherwise false.
     */
    fun isInternetConnected(): Boolean {
        return networkUtil.isInternetAvailable()
    }
}