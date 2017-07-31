package com.wiacek.weatherapp.di.modules

import android.net.ConnectivityManager
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.di.scopes.ActivityScope
import com.wiacek.weatherapp.ui.weather.WeatherActivity
import com.wiacek.weatherapp.ui.weather.WeatherViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class WeatherActivityModule(val weatherActivity: WeatherActivity) {
    @Provides
    @ActivityScope
    fun provideWeatherRepository(openWeatherMapService: OpenWeatherMapService,
                                 connectivityManager: ConnectivityManager): WeatherRepository {
        return WeatherRepository(openWeatherMapService, connectivityManager)
    }

    @Provides
    @ActivityScope
    fun provideWeatherViewModel(weatherRepository: WeatherRepository): WeatherViewModel {
        return WeatherViewModel(weatherRepository)
    }
}