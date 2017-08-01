package com.wiacek.weatherapp.ui.weather

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wiacek.weatherapp.BR
import com.wiacek.weatherapp.data.model.WeatherCondition
import java.text.SimpleDateFormat
import java.util.*

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

    fun showLoadingIndicator() {
        isLoadingVisible = true
    }

    fun hideLoadingIndicator() {
        isLoadingVisible = false
    }

    fun showOnlineData() {
        isDataVisible = true
    }

    fun fillViewModelData(weatherCondition: WeatherCondition) {
        currentCondition = weatherCondition.weatherDescription
        temperature = weatherCondition.temperature + " \u2103"
        windSpeed = weatherCondition.windSpeed
        windDirection = weatherCondition.windDirection
        iconUrl = weatherCondition.iconUrl
    }

    fun showErrorMessage() {
        isErrorMessageVisible = true
        isFabButtonVisible = true
    }

    fun disableAllViews() {
        isFabButtonVisible = false
        isLastUpdateDateVisible = false
        isDataVisible = false
        isNoInternetInfoVisible = false
        isScreenNoDataVisible = false
        isOfflineMessageVisible = false
        isErrorMessageVisible = false
    }

    fun showNoOfflineData() {
        isScreenNoDataVisible = true
        isFabButtonVisible = true
    }

    fun showOfflineData() {
        isDataVisible = true
        isLastUpdateDateVisible = true
        isFabButtonVisible = true
    }

    fun showOfflineDataLastUpdateDate(createDate: Long) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        lastUpdateDate = sdf.format(Date(createDate))
    }

    fun showOfflineMessage() {
        isOfflineMessageVisible = true
        isFabButtonVisible = true
    }
}