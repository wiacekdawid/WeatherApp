package com.wiacek.weatherapp.data.db

import com.wiacek.weatherapp.data.model.WeatherCondition
import io.realm.annotations.RealmModule
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

@RealmModule(classes = arrayOf(WeatherCondition::class))
open class ModelsRealmModule @Inject constructor()