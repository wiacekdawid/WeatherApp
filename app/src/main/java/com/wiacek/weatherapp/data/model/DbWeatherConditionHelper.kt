package com.wiacek.weatherapp.data.model

import io.reactivex.Completable
import io.realm.Realm

/**
 * Created by wiacek.dawid@gmail.com
 */

object DbWeatherConditionHelper {

    fun add(realm: Realm, weatherCondition: WeatherCondition): Completable = Completable.fromAction {
        realm.executeTransaction { it -> it.insertOrUpdate(weatherCondition) }
    }

    fun getLatest(realm: Realm): WeatherCondition? = realm
            .where(WeatherCondition::class.java)
            .findAll()
            .sort(WeatherConditionFields.CREATE_DATE)
            .toList()
            .firstOrNull()

}