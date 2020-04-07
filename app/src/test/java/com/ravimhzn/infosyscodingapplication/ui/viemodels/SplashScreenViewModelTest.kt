package com.ravimhzn.infosyscodingapplication.ui.viemodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ravimhzn.infosyscodingapplication.utils.NetworkUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class SplashScreenViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var networkUtil: NetworkUtil

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `checkConnectionAndNavigate when you have network connection`() {
        networkUtil = mock { on { isInternetAvailable() } doReturn true }
        val viewModel = SplashScreenViewModel(
            networkUtil = networkUtil
        )
        viewModel.checkConnectionAndNavigate()
        viewModel.navigateToNextScreen.observeForever {}
        assertNotNull(viewModel.navigateToNextScreen.value)
    }

    @Test
    fun `checkConnectionAndNavigate when you have no network connection`() {
        networkUtil = mock { on { isInternetAvailable() } doReturn false }
        val viewModel = SplashScreenViewModel(
            networkUtil = networkUtil
        )
        viewModel.checkConnectionAndNavigate()
        viewModel.navigateToNextScreen.observeForever {}
        assertNull(viewModel.navigateToNextScreen.value)
    }

    @Test
    fun `isInternetConnected and checkInternetConnection while internet is available`() {
        val viewModel = SplashScreenViewModel(
            networkUtil = mock { on { isInternetAvailable() } doReturn true }
        )
        assertTrue(viewModel.isInternetConnected())
        assertTrue(viewModel.checkInternetConnection())
    }

    @Test
    fun `isInternetConnected and checkInternetConnection while internet is not available`() {
        val viewModel = SplashScreenViewModel(
            networkUtil = mock { on { isInternetAvailable() } doReturn false }
        )
        assertFalse(viewModel.isInternetConnected())
        assertFalse(viewModel.checkInternetConnection())
    }

    @Test
    fun `checkConnectionAndNavigate when you have internet connection`() {
        val viewModel = SplashScreenViewModel(
            networkUtil = mock { on { isInternetAvailable() } doReturn true }
        )
        viewModel.checkConnectionAndNavigate()
        viewModel.navigateToNextScreen.observeForever {}
        assertNotNull(viewModel.navigateToNextScreen.value)
    }

    @Test
    fun `checkConnectionAndNavigate when you don't have internet connection`() {
        val viewModel = SplashScreenViewModel(
            networkUtil = mock { on { isInternetAvailable() } doReturn false }
        )
        viewModel.checkConnectionAndNavigate()
        viewModel.navigateToNextScreen.observeForever {}
        assertNull(viewModel.navigateToNextScreen.value)
    }


}