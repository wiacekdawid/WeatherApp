package com.wiacek.weatherapp.ui.weather

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.wiacek.weatherapp.R
import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.databinding.ActivityWeatherBinding
import com.wiacek.weatherapp.di.modules.WeatherActivityModule
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherActivity : AppCompatActivity(), PermissionManageRequest {
    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    @Inject
    lateinit var weatherViewHandler: WeatherViewHandler

    val PERMISSION_REQUEST_LOCATION = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        var component = WeatherApplication.get(this).appComponent.add(WeatherActivityModule(this))
        component.inject(this)
        super.onCreate(savedInstanceState)

        weatherViewHandler.permisionManageRequest = this

        var binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
        binding.viewModel = weatherViewModel
        binding.viewHandler = weatherViewHandler
    }

    override fun onResume() {
        super.onResume()
        weatherViewHandler.refreshWeatherConditions()
    }

    override fun verifyPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    (weatherViewHandler as PermissionManageResult).permissionWasGranted()
                }
            }
            else -> Timber.i("onRequestPermissionsResult was called with code: " + requestCode)
        }
    }

    override fun requestPermission(permission: String) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            AlertDialog.Builder(this)
                    .setTitle(resources.getString(R.string.permission_alert_dialog_title))
                    .setMessage(resources.getString(R.string.permission_alert_dialog_message))
                    .setPositiveButton(resources.getString(R.string.permission_alert_dialog_positive_button),
                            DialogInterface.OnClickListener { dialog, which ->
                                ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_LOCATION)
                            })
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_LOCATION)
        }
    }
}