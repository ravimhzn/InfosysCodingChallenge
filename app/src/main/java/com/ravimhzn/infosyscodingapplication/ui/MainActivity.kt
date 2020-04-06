package com.ravimhzn.infosyscodingapplication.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.ravimhzn.infosyscodingapplication.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    /**
     * To display how we can use Navigation from Android Jetpack, I'll be using two Fragments:
     * SplashScreen and AboutFrag
     */
    private fun initNavigation() {
        supportFragmentManager
            .findFragmentById(R.id.main_navigation) as NavHostFragment?
            ?: return
    }
}
