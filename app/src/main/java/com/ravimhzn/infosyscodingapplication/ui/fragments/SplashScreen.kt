package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.extension.navigate
import com.ravimhzn.infosyscodingapplication.ui.viemodels.SplashScreenViewModel
import com.ravimhzn.infosyscodingapplication.utils.data.EventObserver
import com.ravimhzn.openweatherapp.modules.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import javax.inject.Inject

/**
 * We will use SplashScreen to see if there is an internet connection or not.
 */
class SplashScreen : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashScreenViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationView.playAnimation()
        startObserving()
    }

    private fun startObserving() {
        viewModel.navigateToNextScreen.observe(viewLifecycleOwner, EventObserver {
            navigateToAboutFrag(true)
        })

        viewModel.connectionErrorMessage.observe(viewLifecycleOwner, Observer { noConnection ->
            if (noConnection)
            //TODO
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
            navigateToAboutFrag(false)
        })
    }

    private fun navigateToAboutFrag(status: Boolean) {
        navigate(
            actionId = R.id.action_splashScreen_to_aboutFrag,
            bundle = AboutFrag.toBundle(status)
        )
    }
}
