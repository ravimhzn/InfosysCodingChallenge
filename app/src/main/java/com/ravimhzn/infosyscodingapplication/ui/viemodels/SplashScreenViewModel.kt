package com.ravimhzn.infosyscodingapplication.ui.viemodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.utils.NetworkUtil
import com.ravimhzn.infosyscodingapplication.utils.data.Event
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val networkUtil: NetworkUtil
) : ViewModel() {

    private val _navigateToNextScreen: MediatorLiveData<Event<Unit>> = MediatorLiveData()
    val navigateToNextScreen: LiveData<Event<Unit>>
        get() = _navigateToNextScreen

    private val _connectionErrorMessage: MutableLiveData<Boolean> = MutableLiveData()
    val connectionErrorMessage: LiveData<Boolean>
        get() = _connectionErrorMessage

    init {
        checkConnectionAndNavigate()
    }

    /**
     * Checks if the device is connected to the internet and if connected navigate user to About Canada Page
     * <p>
     * In case of no connection, the checks won't be done and user will see an error message
     */
    fun checkConnectionAndNavigate() {
        checkInternetConnection().let { connected ->
            if (connected) {
                _navigateToNextScreen.value = Event(Unit)
            }
        }
    }

    /**
     * Checks if the device is connected to an internet. Put the result in the corresponding observable.
     */
    fun checkInternetConnection(): Boolean {
        val connected = isInternetConnected()
        _connectionErrorMessage.value = connected.not()
        return connected
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