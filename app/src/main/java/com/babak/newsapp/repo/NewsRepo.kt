package com.babak.newsapp.repo

import com.babak.newsapp.api.ApiClient
import com.babak.newsapp.model.NewsResponseModel
import retrofit2.Response

class NewsRepo {
    private val api= ApiClient.createApi()
   suspend fun getAllNews():Response<NewsResponseModel>{
        return api.getAllNews("Azeri")
    }
}