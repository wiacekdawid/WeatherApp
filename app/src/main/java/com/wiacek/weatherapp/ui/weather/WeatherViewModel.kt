package com.wiacek.weatherapp.ui.weather

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.weatherapp.BR

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewModel(currentCondition: String = "",
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

}