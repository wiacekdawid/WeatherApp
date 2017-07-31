package com.wiacek.weatherapp.ui.weather

import android.databinding.DataBindingUtil
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wiacek.weatherapp.R
import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.databinding.ActivityWeatherBinding
import com.wiacek.weatherapp.di.modules.WeatherActivityModule
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherRepository: WeatherRepository
    @Inject
    lateinit var locationManager: LocationManager
    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    @Inject
    lateinit var weatherViewHandler: WeatherViewHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        var component = WeatherApplication.get(this).appComponent.add(WeatherActivityModule(this))
        component.inject(this)
        super.onCreate(savedInstanceState)

        var binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
        binding.viewModel = weatherViewModel
        binding.viewHandler = weatherViewHandler
    }

    override fun onResume() {
        super.onResume()
        weatherViewHandler.refreshWeatherConditions()
    }
}