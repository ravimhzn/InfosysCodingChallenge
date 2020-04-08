package com.ravimhzn.infosyscodingapplication.ui.fragments

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.utils.extension.navigate
import com.ravimhzn.infosyscodingapplication.ui.viemodels.SplashScreenViewModel
import com.ravimhzn.infosyscodingapplication.utils.data.EventObserver
import com.ravimhzn.openweatherapp.modules.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import javax.inject.Inject


/**
 * We will use SplashScreen to see if there is an internet connection or not.
 * Using Only LiveData on this fragment
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
        lottieAnimation()
    }

    /**
     * Usually we would load configuration on Splash and navigate accoringly.
     * In this case, I'm just playing the animation first and start observing
     */
    private fun lottieAnimation() {
        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                try {
                    startObserving()
                } catch (ex: Exception) {
                    ex.toString()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
    }

    private fun startObserving() {
        viewModel.navigateToNextScreen.observe(viewLifecycleOwner, EventObserver {
            navigateToAboutFrag(true)
        })

        viewModel.connectionErrorMessage.observe(viewLifecycleOwner, Observer { noConnection ->
            if (noConnection)
                navigateToAboutFrag(false)
        })
    }

    private fun navigateToAboutFrag(status: Boolean) {
        //TODO(Backstack not cleared -> Bug)
        navigate(
            actionId = R.id.action_splashScreen_to_aboutFrag,
            bundle = AboutFrag.toBundle(status)
        )

    }
}
