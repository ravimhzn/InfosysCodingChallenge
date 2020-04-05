package com.ravimhzn.infosyscodingapplication.ui.di

import com.ravimhzn.infosyscodingapplication.ui.fragments.AboutFrag
import com.ravimhzn.infosyscodingapplication.ui.fragments.SplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashScreen(): SplashScreen

    @ContributesAndroidInjector
    abstract fun contributeAboutFrag(): AboutFrag


}