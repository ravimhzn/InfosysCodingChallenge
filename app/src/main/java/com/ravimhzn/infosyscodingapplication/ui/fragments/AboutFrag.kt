package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.ui.viemodels.AboutFragViewModel
import com.ravimhzn.infosyscodingapplication.utils.data.map
import com.ravimhzn.openweatherapp.modules.BaseFragment
import javax.inject.Inject


class AboutFrag : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AboutFragViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservingForData()
    }

    private fun startObservingForData() {
        viewModel.countryResult.removeObservers(viewLifecycleOwner)
        viewModel.countryResult.observe(viewLifecycleOwner, Observer { it ->
            it.map {
                println("Debug -> Title:: ${it.title}")
            }
        })
    }

}
