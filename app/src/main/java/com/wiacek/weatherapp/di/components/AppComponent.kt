package com.wiacek.weatherapp.di.components

import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.di.modules.AppModule
import com.wiacek.weatherapp.di.modules.NetModule
import com.wiacek.weatherapp.di.modules.WeatherActivityModule
import com.wiacek.weatherapp.di.scopes.ApplicationScope
import dagger.Component

/**
 * Created by wiacek.dawid@gmail.com
 */

@ApplicationScope
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(application: WeatherApplication)
    fun add(module: WeatherActivityModule): WeatherActivityComponent
}