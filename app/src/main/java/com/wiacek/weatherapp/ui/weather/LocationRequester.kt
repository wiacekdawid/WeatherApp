package com.wiacek.weatherapp.ui.weather

import android.location.Location

/**
 * Created by wiacek.dawid@gmail.com
 */
interface LocationRequester {
    fun getLocation(): Location?
}