package com.wiacek.weatherapp

import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary

/**
 * Created by wiacek.dawid@gmail.com
 */

class FlickrGalleryApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeLeakCanary()
    }

    private fun initializeLeakCanary() {
        if(!LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}