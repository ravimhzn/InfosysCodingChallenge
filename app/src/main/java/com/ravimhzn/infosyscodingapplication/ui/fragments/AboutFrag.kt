package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.databinding.FragmentAboutBinding
import com.ravimhzn.infosyscodingapplication.ui.viemodels.AboutFragListViewModel
import com.ravimhzn.openweatherapp.modules.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject

/**
 * We will only use Rx for this fragment
 */
class AboutFrag : BaseFragment() {

    private lateinit var binding: FragmentAboutBinding
    private var errorSnackbar: Snackbar? = null
    private var status: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AboutFragListViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status = fromBundle(this).networkStatus!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (status) {
            showError(R.string.no_connection)
        }
        initToolbar()
        initRecyclerView()
        startObserver()
    }

    private fun initToolbar() {
        layout_refresh.setOnClickListener {
            startObserver()
        }
    }

    private fun startObserver() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun initRecyclerView() {
        binding.recyclerList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showToastError(@StringRes msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
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