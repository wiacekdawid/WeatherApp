package com.wiacek.weatherapp.ui.weather

/**
 * Created by wiacek.dawid@gmail.com
 */
interface PermissionManageRequest {
    fun verifyPermission(permission: String): Boolean
    fun requestPermission(permission: String)
}