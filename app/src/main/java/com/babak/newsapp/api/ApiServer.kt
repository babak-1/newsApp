package com.babak.newsapp.api

import com.babak.newsapp.model.NewsResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    @GET("v2/everything")
    suspend fun getAllNews(@Query("q")query:String,@Query("apiKey")apiKey:String="1707015adf0a4977a6bf527f3633bc67"):Response<NewsResponseModel>
}