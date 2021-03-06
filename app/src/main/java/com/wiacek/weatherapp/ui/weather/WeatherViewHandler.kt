package com.wiacek.weatherapp.ui.weather

import android.location.Location
import com.wiacek.weatherapp.data.WeatherDataManager
import com.wiacek.weatherapp.util.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewHandler(val attachedWeatherActivity: AttachedWeatherActivity,
                         val weatherViewModel: WeatherViewModel,
                         val weatherDataManager: WeatherDataManager,
                         val networkManager: NetworkManager) {

    fun refreshWeatherConditions() {
        weatherViewModel.disableAllViews()
        weatherViewModel.showLoadingIndicator()
        var location: Location? = attachedWeatherActivity.getLocation()

        if(networkManager.isInternetOn() && location != null) {
            weatherDataManager.getWeatherConditionByLatLonRemote(location.latitude, location.longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                weatherViewModel.showOnlineData()
                                weatherViewModel.fillViewModelData(weatherCondition = it)
                                weatherViewModel.hideLoadingIndicator()
                            },
                            {
                                weatherViewModel.showErrorMessage()
                                weatherViewModel.hideLoadingIndicator()
                                Timber.e(it)
                            }
                    )
        }
        else {
            // if we dont have location we show Toast message
            if(location == null) {
                attachedWeatherActivity.showNoLocationToastMessage()
            }

            weatherDataManager.getLatestWeatherConditionLocal()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                weatherViewModel.showOfflineData()
                                weatherViewModel.showOfflineDataLastUpdateDate(it.createDate)
                                weatherViewModel.fillViewModelData(weatherCondition = it)
                                weatherViewModel.hideLoadingIndicator()
                            },
                            {
                                weatherViewModel.showErrorMessage()
                                weatherViewModel.hideLoadingIndicator()
                                Timber.e(it)
                            },
                            {
                                weatherViewModel.showNoOfflineData()
                                weatherViewModel.hideLoadingIndicator()
                            }
                    )
        }
    }

    fun refreshManually() {
        if(!networkManager.isInternetOn()) {
            weatherViewModel.disableAllViews()
            weatherViewModel.showOfflineMessage()
        }
        else {
            refreshWeatherConditions()
        }
    }

    fun permissionWasGranted() {
        refreshWeatherConditions()
    }
}