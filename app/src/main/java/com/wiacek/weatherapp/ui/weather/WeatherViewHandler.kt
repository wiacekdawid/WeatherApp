package com.wiacek.weatherapp.ui.weather

import android.Manifest
import android.location.Location
import android.location.LocationManager
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.util.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */
class WeatherViewHandler(val weatherViewModel: WeatherViewModel,
                         val weatherRepository: WeatherRepository,
                         val networkManager: NetworkManager,
                         val locationManager: LocationManager) : PermissionManageResult {

    var permisionManageRequest: PermissionManageRequest? = null

    fun refreshWeatherConditions() {
        weatherViewModel.disableAllViews()
        weatherViewModel.showLoadingIndicator()
        var location: Location? = null
        if(permisionManageRequest != null) {
            location = getLocation()
        }

        if(networkManager.isInternetOn() && location != null) {
            weatherRepository.getWeatherConditionByLatLonRemote(location.latitude, location.longitude)
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
            weatherRepository.getLatestWeatherConditionLocal()
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

    fun getLocation(): Location? {
        if((permisionManageRequest as PermissionManageRequest).verifyPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        (permisionManageRequest as PermissionManageRequest).requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        return null
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

    override fun permissionWasGranted() {
        refreshWeatherConditions()
    }
}