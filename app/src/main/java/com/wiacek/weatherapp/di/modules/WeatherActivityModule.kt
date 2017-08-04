package com.wiacek.weatherapp.di.modules

import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.di.scopes.ActivityScope
import com.wiacek.weatherapp.ui.weather.WeatherActivity
import com.wiacek.weatherapp.ui.weather.WeatherViewHandler
import com.wiacek.weatherapp.ui.weather.WeatherViewModel
import com.wiacek.weatherapp.util.NetworkManager
import dagger.Module
import dagger.Provides

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class WeatherActivityModule(val weatherActivity: WeatherActivity) {
    @Provides
    @ActivityScope
    fun provideWeatherRepository(openWeatherMapService: OpenWeatherMapService): WeatherRepository {
        return WeatherRepository(openWeatherMapService)
    }

    @Provides
    @ActivityScope
    fun provideWeatherViewModel(): WeatherViewModel {
        return WeatherViewModel()
    }

    @Provides
    @ActivityScope
    fun provideWeatherViewHandler(weatherViewModel: WeatherViewModel,
                                  weatherRepository: WeatherRepository,
                                  networkManager: NetworkManager): WeatherViewHandler {
        return WeatherViewHandler(weatherViewModel, weatherRepository, networkManager)
    }
}