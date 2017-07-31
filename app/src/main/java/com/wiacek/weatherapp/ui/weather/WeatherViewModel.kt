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
                       windDirection: String = "",
                       iconUrl: String = "",
                       isLoadingVisible: Boolean = false,
                       isFabButtonVisible: Boolean = false,
                       isLastUpdateDateVisible: Boolean = false,
                       isNoInternetInfoVisible: Boolean = false,
                       isScreenNoDataVisible: Boolean = false,
                       isDataVisible: Boolean = false,
                       isOfflineMessageVisible: Boolean = false,
                       lastUpdateDate: String = "",
                       isErrorMessageVisible: Boolean = false) : BaseObservable() {

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

    @get:Bindable
    var isLoadingVisible = isLoadingVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingVisible)
        }

    @get:Bindable
    var isFabButtonVisible = isFabButtonVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.fabButtonVisible)
        }

    @get:Bindable
    var isLastUpdateDateVisible = isLastUpdateDateVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastUpdateDateVisible)
        }

    @get:Bindable
    var isNoInternetInfoVisible = isNoInternetInfoVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.noInternetInfoVisible)
        }

    @get:Bindable
    var isScreenNoDataVisible = isScreenNoDataVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.screenNoDataVisible)
        }

    @get:Bindable
    var isDataVisible = isDataVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataVisible)
        }

    @get:Bindable
    var isOfflineMessageVisible = isOfflineMessageVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.offlineMessageVisible)
        }

    @get:Bindable
    var lastUpdateDate = lastUpdateDate
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastUpdateDate)
        }

    @get:Bindable
    var isErrorMessageVisible = isErrorMessageVisible
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorMessageVisible)
        }
}