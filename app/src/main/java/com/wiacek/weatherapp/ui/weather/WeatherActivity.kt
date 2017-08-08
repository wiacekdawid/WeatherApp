package com.wiacek.weatherapp.ui.weather

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wiacek.weatherapp.R
import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.databinding.ActivityWeatherBinding
import com.wiacek.weatherapp.di.modules.WeatherActivityModule
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherActivity : AppCompatActivity() {
    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    @Inject
    lateinit var weatherViewHandler: WeatherViewHandler
    @Inject
    lateinit var locationManager: LocationManager

    val PERMISSION_REQUEST_LOCATION = 999

    val WEATHER_VIEW_MODEL_STATE = "weatherViewModelState"

    override fun onCreate(savedInstanceState: Bundle?) {
        var component = WeatherApplication.get(this).appComponent.add(WeatherActivityModule(WeakReference(this)))
        component.inject(this)
        super.onCreate(savedInstanceState)

        var binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)

        if(savedInstanceState != null) {
            weatherViewModel = savedInstanceState.getParcelable(WEATHER_VIEW_MODEL_STATE)
        }
        else {
            weatherViewHandler.refreshWeatherConditions()
        }

        binding.viewModel = weatherViewModel
        binding.viewHandler = weatherViewHandler
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(WEATHER_VIEW_MODEL_STATE, weatherViewModel)
    }

    fun getLocation(): Location? {
        if(verifyPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        return null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    weatherViewHandler.permissionWasGranted()
                }
                else {
                    /* we already match never ask again option for location permission and we inform about it
                        user by Toast message */
                    Toast.makeText(this, R.string.permission_needed_when_never_ask_again_active, Toast.LENGTH_LONG).show()
                }
            }
            else -> Timber.i("onRequestPermissionsResult was called with code: " + requestCode)
        }
    }

    fun showNoLocationToastMessage() = Toast.makeText(this, R.string.no_location_message, Toast.LENGTH_SHORT).show()

    fun verifyPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(permission: String) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showAlertDialogShowRequestPermissionRationale(permission)
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_LOCATION)
        }
    }

    private fun showAlertDialogShowRequestPermissionRationale(permission: String) {
        AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.permission_alert_dialog_title))
                .setMessage(resources.getString(R.string.permission_alert_dialog_message))
                .setPositiveButton(resources.getString(R.string.permission_alert_dialog_positive_button),
                        { dialog, which ->
                            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_LOCATION)
                        })
                .setNegativeButton(resources.getString(R.string.permission_alert_dialog_negative_button),
                        { dialog, which -> dialog.dismiss() })
                .show()
    }
}