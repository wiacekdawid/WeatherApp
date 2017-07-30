package com.wiacek.weatherapp.bindings

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by wiacek.dawid@gmail.com
 */
@BindingAdapter("android:url")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}