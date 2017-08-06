package com.wiacek.weatherapp.di.modules

import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.di.scopes.ActivityScope
import com.wiacek.weatherapp.ui.weather.*
import com.wiacek.weatherapp.util.NetworkManager
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class WeatherActivityModule(val weatherActivity: WeakReference<WeatherActivity>) {
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
    fun provideAttachedWeatherActivity(): AttachedWeatherActivity {
        return AttachedWeatherActivityImp(weatherActivity)
    }

    @Provides
    @ActivityScope
    fun provideWeatherViewHandler(attachedWeatherActivity: AttachedWeatherActivity,
                                  weatherViewModel: WeatherViewModel,
                                  weatherRepository: WeatherRepository,
                                  networkManager: NetworkManager): WeatherViewHandler {
        return WeatherViewHandler(attachedWeatherActivity, weatherViewModel, weatherRepository, networkManager)
    }
}