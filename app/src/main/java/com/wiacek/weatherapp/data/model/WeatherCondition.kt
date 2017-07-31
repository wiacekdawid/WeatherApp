package com.wiacek.weatherapp.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by wiacek.dawid@gmail.com
 */

@RealmClass
open class WeatherCondition(
        @PrimaryKey
        var uuid: String = UUID.randomUUID().toString(),
        var name: String = "",
        var weatherDescription: String = "",
        var temperature: String = "",
        var windSpeed: String = "",
        var windDirection: String = "",
        var iconUrl: String = "",
        var createDate: Long = System.currentTimeMillis()) : RealmObject()