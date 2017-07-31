package com.wiacek.weatherapp.bindings

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by wiacek.dawid@gmail.com
 */

@BindingAdapter("android:visibility")
fun bindVisibility(view: View, isVisible: Boolean) {
    view.visibility = if(isVisible) View.VISIBLE else View.GONE
}