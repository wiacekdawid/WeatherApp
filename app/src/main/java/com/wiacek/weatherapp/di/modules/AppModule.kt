package com.wiacek.weatherapp.di.modules

import android.content.Context
import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class AppModule(var application: WeatherApplication) {

    @Provides
    @Named("ApplicationContext")
    @ApplicationScope
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }
}