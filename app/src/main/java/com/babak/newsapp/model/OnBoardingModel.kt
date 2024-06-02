package com.babak.newsapp.model

import androidx.annotation.DrawableRes

data class OnBoardingModel (
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)
