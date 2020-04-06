package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.ui.adapter.AboutRecyclerAdapter
import com.ravimhzn.infosyscodingapplication.ui.model.CountryInfoDataModel
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.ui.viemodels.AboutFragViewModel
import com.ravimhzn.infosyscodingapplication.utils.data.map
import com.ravimhzn.openweatherapp.modules.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject


class AboutFrag : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AboutFragViewModel by viewModels { viewModelFactory }

    private var status: Boolean? = false

    @Inject
    lateinit var aboutRecyclerAdapter: AboutRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status = fromBundle(this).networkStatus
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        if (status!!) {
            startObservingForData()
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = aboutRecyclerAdapter
    }

    private fun startObservingForData() {
        viewModel.countryResult.removeObservers(viewLifecycleOwner)
        viewModel.countryResult.observe(viewLifecycleOwner, Observer { it ->
            it.map {
                setUpViews(it)
            }
        })
    }

    private fun setUpViews(countryData: CountryInfoDataModel) {
        countryData.rows?.let { aboutRecyclerAdapter.setCountryInfo(it as List<Row>) }
    }

    companion object {
        private const val BUNDLE_KEY_TITLE = "network"

        fun toBundle(networkStatus: Boolean) = Bundle().apply {
            putBoolean(BUNDLE_KEY_TITLE, networkStatus)
        }

        fun fromBundle(fragment: AboutFrag): AboutFragArgs {
            val bundle: Bundle? = fragment.arguments
            val status = bundle?.getBoolean(BUNDLE_KEY_TITLE)
            return AboutFragArgs(status)
        }
    }
}

data class AboutFragArgs(
    val networkStatus: Boolean? = false
)
