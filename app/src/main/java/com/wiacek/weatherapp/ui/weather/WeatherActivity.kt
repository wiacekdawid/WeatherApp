package com.wiacek.weatherapp.ui.weather

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wiacek.weatherapp.R
import com.wiacek.weatherapp.databinding.ActivityWeatherBinding

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
    }
}