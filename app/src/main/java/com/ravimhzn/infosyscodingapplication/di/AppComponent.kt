package com.ravimhzn.openweatherapp.di

import com.ravimhzn.infosyscodingapplication.di.ActivityBuildersModule
import com.ravimhzn.infosyscodingapplication.di.AppModule
import com.ravimhzn.infosyscodingapplication.di.BaseApplication
import com.ravimhzn.infosyscodingapplication.di.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)

interface AppComponent {
    fun inject(app: BaseApplication)
}

