package com.wiacek.weatherapp.ui.weather

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wiacek.weatherapp.R
import com.wiacek.weatherapp.databinding.ActivityWeatherBinding
import com.wiacek.weatherapp.api.OpenWeatherMapService
import com.wiacek.weatherapp.di.components.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var openWeatherMapService: OpenWeatherMapService

    var weatherViewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        var component = DaggerAppComponent.builder().build()
        component.inject(this)

        super.onCreate(savedInstanceState)

        weatherViewModel.openWeatherMapService = openWeatherMapService

        var binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
        binding.viewModel = weatherViewModel
    }

    override fun onResume() {
        super.onResume()
        weatherViewModel.refreshWeatherConditions()
    }
}