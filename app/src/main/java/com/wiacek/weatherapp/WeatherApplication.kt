package com.wiacek.weatherapp

import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import com.wiacek.weatherapp.di.components.AppComponent
import com.wiacek.weatherapp.di.components.DaggerAppComponent
import com.wiacek.weatherapp.di.modules.AppModule

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherApplication: MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDI()
        initializeLeakCanary()
    }

    private fun initializeDI() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    private fun initializeLeakCanary() {
        if(!LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}