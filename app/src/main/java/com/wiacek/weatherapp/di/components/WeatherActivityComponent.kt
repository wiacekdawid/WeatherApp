package com.wiacek.weatherapp.di.components

import com.wiacek.weatherapp.di.modules.WeatherActivityModule
import com.wiacek.weatherapp.di.scopes.ActivityScope
import com.wiacek.weatherapp.ui.weather.WeatherActivity
import dagger.Subcomponent

/**
 * Created by wiacek.dawid@gmail.com
 */

@ActivityScope
@Subcomponent(modules = arrayOf(WeatherActivityModule::class))
interface WeatherActivityComponent {
    fun inject(activity: WeatherActivity)
}