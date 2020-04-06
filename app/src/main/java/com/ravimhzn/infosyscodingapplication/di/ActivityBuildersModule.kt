package com.ravimhzn.infosyscodingapplication.di


import com.ravimhzn.infosyscodingapplication.ui.MainActivity
import com.ravimhzn.infosyscodingapplication.ui.di.MainFragmentBuildersModule
import com.ravimhzn.infosyscodingapplication.ui.di.MainModule
import com.ravimhzn.infosyscodingapplication.ui.di.MainViewModelModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
