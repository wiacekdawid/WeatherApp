package com.wiacek.weatherapp.ui.weather

import android.location.LocationManager
import android.net.ConnectivityManager
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.data.model.WeatherCondition
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewHandler(val weatherViewModel: WeatherViewModel,
                        val weatherRepository: WeatherRepository,
                         val connectivityManager: ConnectivityManager,
                         val locationManager: LocationManager) {

    fun refreshWeatherConditions() {
        disableAllViews()
        showLoadingIndicator()
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if(isInternetOn() && location != null) {
            weatherRepository.getWeatherConditionByLatLonRemote(location.latitude, location.longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                showOnlineData()
                                fillViewModelData(weatherCondition = it)
                                hideLoadingIndicator()
                            },
                            {
                                showErrorMessage()
                                hideLoadingIndicator()
                                Timber.e(it)
                            }
                    )
        }
        else {
            weatherRepository.getLatestWeatherConditionLocal()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                showOfflineData()
                                showOfflineDataLastUpdateDate(it.createDate)
                                fillViewModelData(weatherCondition = it)
                                hideLoadingIndicator()
                            },
                            {
                                showErrorMessage()
                                hideLoadingIndicator()
                                Timber.e(it)
                            },
                            {
                                showNoOfflineData()
                                hideLoadingIndicator()
                            }
                    )
        }
    }

    fun refreshManually() {
        if(!isInternetOn()) {
            disableAllViews()
            weatherViewModel.isOfflineMessageVisible = true
            weatherViewModel.isFabButtonVisible = true
        }
        else {
            refreshWeatherConditions()
        }
    }

    private fun isInternetOn(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
    }

    fun showLoadingIndicator() {
        weatherViewModel.isLoadingVisible = true
    }

    fun hideLoadingIndicator() {
        weatherViewModel.isLoadingVisible = false
    }

    fun showOnlineData() {
        weatherViewModel.isDataVisible = true
    }

    fun fillViewModelData(weatherCondition: WeatherCondition) {
        weatherViewModel.currentCondition = weatherCondition.weatherDescription
        weatherViewModel.temperature = weatherCondition.temperature + " \u2103"
        weatherViewModel.windSpeed = weatherCondition.windSpeed
        weatherViewModel.windDirection = weatherCondition.windDirection
        weatherViewModel.iconUrl = weatherCondition.iconUrl
    }

    fun showErrorMessage() {
        weatherViewModel.isErrorMessageVisible = true
        weatherViewModel.isFabButtonVisible = true
    }

    fun disableAllViews() {
        weatherViewModel.isFabButtonVisible = false
        weatherViewModel.isLastUpdateDateVisible = false
        weatherViewModel.isDataVisible = false
        weatherViewModel.isNoInternetInfoVisible = false
        weatherViewModel.isScreenNoDataVisible = false
        weatherViewModel.isOfflineMessageVisible = false
        weatherViewModel.isErrorMessageVisible = false
    }

    fun showNoOfflineData() {
        weatherViewModel.isScreenNoDataVisible = true
        weatherViewModel.isFabButtonVisible = true
    }

    fun showOfflineData() {
        weatherViewModel.isDataVisible = true
        weatherViewModel.isLastUpdateDateVisible = true
        weatherViewModel.isFabButtonVisible = true
    }

    fun showOfflineDataLastUpdateDate(createDate: Long) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        weatherViewModel.lastUpdateDate = sdf.format(Date(createDate))
    }
}