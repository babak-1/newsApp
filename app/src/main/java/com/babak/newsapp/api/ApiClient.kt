package com.babak.newsapp.api

class ApiClient {
    companion object{
        fun createApi():ApiServer{
            return RetrofitClient.retrofit.create(ApiServer::class.java)
        }
    }
}