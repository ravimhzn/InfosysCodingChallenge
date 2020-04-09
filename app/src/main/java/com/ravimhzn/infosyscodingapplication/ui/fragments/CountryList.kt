package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.databinding.FragmentCountryListBinding
import com.ravimhzn.infosyscodingapplication.ui.viemodels.CountryListViewModel
import com.ravimhzn.infosyscodingapplication.utils.data.map
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CountryList : DaggerFragment() {

    private lateinit var binding: FragmentCountryListBinding
    private var isRefreshPressed: Boolean = false
    private var isNetwork: Boolean = false


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CountryListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_country_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        startObserver()
    }

    private fun startObserver() {
//        viewModel.getCountryInforResult.removeObservers(viewLifecycleOwner)
//        viewModel.getCountryInforResult.observe(viewLifecycleOwner, Observer { it ->
//            it.map {
//                println("debug -> From Fragment :: $it")
//                viewModel.onGetDataFromServerSuccess(it)
//            }
//        })
    }

    private fun initRecyclerView() {
        binding.recyclerList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
