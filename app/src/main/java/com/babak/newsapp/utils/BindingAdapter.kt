package com.babak.newsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("load_image_src")
fun setImageSrc(imageView: ImageView,imageId:Int){
    imageView.setImageResource(imageId)
}