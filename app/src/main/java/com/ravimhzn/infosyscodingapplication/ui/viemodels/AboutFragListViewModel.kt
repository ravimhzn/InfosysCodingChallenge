package com.ravimhzn.infosyscodingapplication.ui.viemodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.network.ApiService
import com.ravimhzn.infosyscodingapplication.persistence.CountryInfoDao
import com.ravimhzn.infosyscodingapplication.ui.adapter.AboutRecyclerAdapter
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AboutFragListViewModel @Inject constructor(
    private val countryInfoDao: CountryInfoDao,
    private val apiService: ApiService
) :
    ViewModel() {

    val aboutRecyclerAdapter: AboutRecyclerAdapter = AboutRecyclerAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadData() }
    private lateinit var subscription: Disposable

    init {
        loadData()
    }

    /**
     * If there is no internet connection, this function will load data from local database
     * otherwise it will load data from api service
     */
    private fun loadData() {
        subscription = Observable.fromCallable { countryInfoDao.getCountryDetailsFromDb }
            .concatMap { dbCountryInfoList ->
                if (dbCountryInfoList.isEmpty())
                    apiService.getCountryPosts().map {
                        it.rows?.filter { it?.let { it1 -> checkIfValuesNotNull(it1) }!! }
                    }.concatMap { apiList ->
                        countryInfoDao.insertIntoDbCountryDetails(*apiList.toTypedArray())
                        Observable.just(apiList)
                    }
                else
                    Observable.just(dbCountryInfoList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result as List<Row>) },
                { onRetrievePostListError() }
            )
    }

    /**
     * Filtering list. Checking if there are null
     */
    private fun checkIfValuesNotNull(it: Row): Boolean {
        return it?.title != null && it?.description != null && it?.imageHref != null
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Row>) {
        aboutRecyclerAdapter.setCountryInfo(postList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.unknown_error_message
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}