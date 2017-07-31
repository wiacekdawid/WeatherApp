package com.wiacek.weatherapp.di.modules

import android.location.LocationManager
import android.net.ConnectivityManager
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.di.scopes.ActivityScope
import com.wiacek.weatherapp.ui.weather.WeatherActivity
import com.wiacek.weatherapp.ui.weather.WeatherViewHandler
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
                                  connectivityManager: ConnectivityManager,
                                  locationManager: LocationManager): WeatherViewHandler {
        return WeatherViewHandler(weatherViewModel, weatherRepository, connectivityManager, locationManager)
    }
}