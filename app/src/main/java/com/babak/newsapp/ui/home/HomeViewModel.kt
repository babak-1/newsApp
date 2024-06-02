package com.babak.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babak.newsapp.api.ApiClient
import com.babak.newsapp.model.NewsResponseModel
import com.babak.newsapp.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeViewModel :ViewModel(){
    val repo = NewsRepo()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

            private val _allNews = MutableLiveData<NewsResponseModel>()
            val allNews: LiveData<NewsResponseModel> = _allNews
    fun requestAllNews(){
        _loading.value=true

        viewModelScope.launch(Dispatchers.IO) {


            try {
                val response= repo.getAllNews()
                if(response.isSuccessful){
                    response.body()?.let {
                        it?.let {
                            withContext(Dispatchers.Main){
                                _allNews.value=it
                            }
                        }
                    }
                }
            }catch (e:Exception){

            }
            finally {
                withContext(Dispatchers.Main){
                    _loading.value=false
                }
            }
        }



    }
}