package com.babak.newsapp.utils

import android.view.View
import android.widget.ImageView
import com.babak.newsapp.R
import com.bumptech.glide.Glide

fun View.visible(){
    this.visibility=View.VISIBLE
}

fun View.gone(){
    this.visibility=View.GONE
}

fun View.inVisible(){
    this.visibility=View.INVISIBLE
}

fun ImageView.imageLoad (url:String?){
    Glide
        .with(this)
        .load(url)
        .centerCrop()
//        .placeholder(R.drawable.img)
        .into(this);
}