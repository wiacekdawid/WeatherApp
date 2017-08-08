package com.wiacek.weatherapp.data

import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.data.model.WeatherCondition
import com.wiacek.weatherapp.data.model.DbWeatherConditionHelper
import com.wiacek.weatherapp.data.model.WeatherConditionMapper
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import timber.log.Timber

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherDataManager(val openWeatherMapService: OpenWeatherMapService) {

    private val ONE_HOUR_IN_MILISEC = 1000 * 60 * 60 * 24

    fun getWeatherConditionByLatLonRemote(lat: Double, lon: Double): Single<WeatherCondition> {

        return openWeatherMapService
                .getWeatherConditionByLatLon(lat,
                        lon)
                .map {
                    WeatherConditionMapper.transformWeatherResponseDtoToWeatherCondition(it)
                }
                .doOnSuccess {
                    it -> DbWeatherConditionHelper.add(Realm.getDefaultInstance(), it)
                        .subscribe({}, { Timber.e(it)})
                }
    }

    fun getLatestWeatherConditionLocal(): Maybe<WeatherCondition> {
        var weatherCondition = DbWeatherConditionHelper.getLatest(Realm.getDefaultInstance())

        if(weatherCondition == null || System.currentTimeMillis() - weatherCondition.createDate > ONE_HOUR_IN_MILISEC )
            return Maybe.empty()

        return Maybe.just(weatherCondition)
    }
}