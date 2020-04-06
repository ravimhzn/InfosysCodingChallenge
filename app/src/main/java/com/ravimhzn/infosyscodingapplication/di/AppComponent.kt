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

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }

    fun inject(app: BaseApplication)
}

