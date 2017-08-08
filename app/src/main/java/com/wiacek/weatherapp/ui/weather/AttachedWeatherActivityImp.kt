package com.wiacek.weatherapp.ui.weather

import android.location.Location
import java.lang.ref.WeakReference

/**
 * Created by wiacek.dawid@gmail.com
 */
class AttachedWeatherActivityImp(private val weatherActivity: WeakReference<WeatherActivity>): AttachedWeatherActivity {
    override fun showNoLocationToastMessage() {
        weatherActivity.get()?.showNoLocationToastMessage()
    }

    override fun getLocation(): Location? {
        return weatherActivity.get()?.getLocation()
    }
}