package com.abdulhakeem.weatherapp_kmm.android

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:load_image")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}