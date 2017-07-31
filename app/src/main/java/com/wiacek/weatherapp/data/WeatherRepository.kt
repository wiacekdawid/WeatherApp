package com.wiacek.weatherapp.data

import android.net.ConnectivityManager
import com.wiacek.weatherapp.BuildConfig
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.model.WeatherCondition
import com.wiacek.weatherapp.data.model.WeatherConditionHelper
import com.wiacek.weatherapp.data.model.WeatherConditionMapper
import io.reactivex.Single
import io.realm.Realm
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherRepository(val openWeatherMapService: OpenWeatherMapService,
                        val connectivityManager: ConnectivityManager) {

    fun getWeatherConditionByLatLon(lat: Double, lon: Double): Single<WeatherCondition> {

        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected) {
            return openWeatherMapService
                    .getWeatherConditionByLatLon(lat,
                            lon,
                            BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .map {
                        WeatherConditionMapper.transformWeatherResponseDtoToWeatherCondition(it)
                    }
                    .doOnSuccess {
                        it -> WeatherConditionHelper.add(Realm.getDefaultInstance(), it)
                            .subscribe({}, { Timber.e(it)})
                    }

        }

        return Single.just(WeatherConditionHelper.getLatest(Realm.getDefaultInstance()))
    }
}