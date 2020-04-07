package com.ravimhzn.infosyscodingapplication.ui.viemodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.utils.NetworkUtil
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class AboutFragListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var networkUtil: NetworkUtil
    private lateinit var viewModel: AboutFragListViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = AboutFragListViewModel(
            countryInfoDao = mock(),
            apiService = mock(),
            networkUtil = mock { on { isInternetAvailable() } doReturn true }
        )
    }

    @Test
    fun `isInternetConnected  on AboutFragViewModel while internet is available`() {
        assertTrue(viewModel.isInternetConnected())
    }

    @Test
    fun `isInternetConnected on AboutFragViewModel while internet is not available`() {
        val viewModel = AboutFragListViewModel(
            countryInfoDao = mock(),
            apiService = mock(),
            networkUtil = mock { on { isInternetAvailable() } doReturn false }
        )
        assertFalse(viewModel.isInternetConnected())
    }

    @Test
    fun `checkIfValuesNotNull return true if value is null`() {
        var r = Row(0, "", "", "")
        var result = viewModel.checkIfValuesNotNull(r)
        assertTrue(result)
    }

    @Test
    fun `checkIfValuesNotNull return true if two values is null`() {
        var r = Row(0, "", "", "title")
        var result = viewModel.checkIfValuesNotNull(r)
        assertTrue(result)
    }

    @Test
    fun `checkIfValuesNotNull return true if one value is null`() {
        var r = Row(0, "", "imageHref", "title")
        var result = viewModel.checkIfValuesNotNull(r)
        assertTrue(result)
    }

    @Test
    fun `checkIfValuesNotNull return false if no values is are null`() {
        var r = Row(0, "description", "imageHref", "title")
        var result = viewModel.checkIfValuesNotNull(r)
        assertTrue(result)
    }
}