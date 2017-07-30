package com.wiacek.weatherapp.ui.weather

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.BR
import com.wiacek.weatherapp.BuildConfig
import com.wiacek.weatherapp.data.WeatherConditionMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewModel(var openWeatherMapService: OpenWeatherMapService? = null,
                       currentCondition: String = "",
                       temperature: String = "",
                       windSpeed: String = "",
                       windDirection: String = "") : BaseObservable() {

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

    fun refreshWeatherConditions() {
        openWeatherMapService?.getWeatherConditionByLatLon(49.975253, 19.124248, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.map {
                    WeatherConditionMapper.transformWeatherResponseDtoToWeatherCondition(it)
                }
                ?.subscribe {
                    weatherCondition ->
                    currentCondition = weatherCondition.weatherDescription
                    temperature = weatherCondition.temperature
                    windSpeed = weatherCondition.windSpeed
                    windDirection = weatherCondition.windDirection
                    Timber.e("Error")
                }
    }
}