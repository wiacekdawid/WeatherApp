package com.wiacek.weatherapp.util

import android.net.ConnectivityManager

/**
 * Created by wiacek.dawid@gmail.com
 */

class NetworkManager(val connectivityManager: ConnectivityManager) {
    fun isInternetOn(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
    }
}