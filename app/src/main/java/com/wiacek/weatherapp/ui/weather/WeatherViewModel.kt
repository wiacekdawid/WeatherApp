package com.wiacek.weatherapp.ui.weather

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.BR
import com.wiacek.weatherapp.BuildConfig
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.data.model.WeatherConditionMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewModel(var weatherRepository: WeatherRepository,
                       currentCondition: String = "",
                       temperature: String = "",
                       windSpeed: String = "",
                       windDirection: String = "",
                       iconUrl: String = "") : BaseObservable() {

    @get:Bindable
    var currentCondition = currentCondition
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentCondition)
        }

    @get:Bindable
    var temperature = temperature
        set(value) {
            field = value
            notifyPropertyChanged(BR.temperature)
        }

    @get:Bindable
    var windSpeed = windSpeed
        set(value) {
            field = value
            notifyPropertyChanged(BR.windSpeed)
        }

    @get:Bindable
    var windDirection = windDirection
        set(value) {
            field = value
            notifyPropertyChanged(BR.windDirection)
        }

    @get:Bindable
    var iconUrl = iconUrl
        set(value) {
            field = value
            notifyPropertyChanged(BR.iconUrl)
        }

    fun refreshWeatherConditions() {
        weatherRepository.getWeatherConditionByLatLon(49.975253, 19.124248)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it -> it != null }
                .subscribe(
                        {
                            weatherCondition ->
                            currentCondition = weatherCondition.weatherDescription
                            temperature = weatherCondition.temperature
                            windSpeed = weatherCondition.windSpeed
                            windDirection = weatherCondition.windDirection
                            iconUrl = weatherCondition.iconUrl
                        },
                        {Timber.e("Error")}
                )
    }
}