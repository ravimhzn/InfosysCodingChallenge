package com.ravimhzn.infosyscodingapplication.ui.di

import androidx.lifecycle.ViewModel
import com.ravimhzn.infosyscodingapplication.di.ViewModelKey
import com.ravimhzn.infosyscodingapplication.ui.viemodels.AboutFragViewModel
import com.ravimhzn.infosyscodingapplication.ui.viemodels.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashViewModel(splashScreenViewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutFragViewModel::class)
    abstract fun bindAboutFragViewModel(aboutFragViewModel: AboutFragViewModel): ViewModel

}