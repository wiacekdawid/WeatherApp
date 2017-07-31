package com.wiacek.weatherapp.ui.weather

import android.location.LocationManager
import android.net.ConnectivityManager
import com.wiacek.weatherapp.data.WeatherRepository
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
                                weatherCondition ->
                                weatherViewModel.currentCondition = weatherCondition.weatherDescription
                                weatherViewModel.temperature = weatherCondition.temperature
                                weatherViewModel.windSpeed = weatherCondition.windSpeed
                                weatherViewModel.windDirection = weatherCondition.windDirection
                                weatherViewModel.iconUrl = weatherCondition.iconUrl
                                weatherViewModel.isDataVisible = true
                                hideLoadingIndicator()
                            },
                            {
                                showErrorMessage()
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
                                weatherViewModel.isDataVisible = true
                                weatherViewModel.isLastUpdateDateVisible = true

                                //TODO move higher
                                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                sdf.timeZone = TimeZone.getTimeZone("UTC")
                                weatherViewModel.isLastUpdateDateVisible = true
                                weatherViewModel.lastUpdateDate = sdf.format(Date(it.createDate))

                                weatherViewModel.currentCondition = it.weatherDescription
                                weatherViewModel.temperature = it.temperature
                                weatherViewModel.windSpeed = it.windSpeed
                                weatherViewModel.windDirection = it.windDirection
                                weatherViewModel.iconUrl = it.iconUrl

                                weatherViewModel.isFabButtonVisible = true
                                hideLoadingIndicator()
                            },
                            {
                                showErrorMessage()
                                Timber.e(it)
                            },
                            {
                                weatherViewModel.isLoadingVisible = false
                                weatherViewModel.isScreenNoDataVisible = true
                                weatherViewModel.isFabButtonVisible = true
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

    fun showErrorMessage() {
        weatherViewModel.isErrorMessageVisible = true
        weatherViewModel.isFabButtonVisible = true
        weatherViewModel.isLoadingVisible = false
    }

    fun hideLoadingIndicator() {
        weatherViewModel.isLoadingVisible = false
    }

    fun disableAllViews() {
        weatherViewModel.isFabButtonVisible = false
        weatherViewModel.isLastUpdateDateVisible = false
        weatherViewModel.isDataVisible = false
        weatherViewModel.isNoInternetInfoVisible = false
        weatherViewModel.isNoInternetInfoVisible = false
        weatherViewModel.isScreenNoDataVisible = false
        weatherViewModel.isOfflineMessageVisible = false
        weatherViewModel.isErrorMessageVisible = false
    }

}