package com.babak.newsapp.ui.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.babak.newsapp.R
import com.babak.newsapp.model.OnBoardingModel

class OnboardongViewModel :ViewModel() {
    val onboardingList = MutableLiveData<List<OnBoardingModel>>()

    init {
        createList()
    }

  private fun createList(){
        val list = listOf<OnBoardingModel>(
            OnBoardingModel("salam men geldim","gnrgnrgogggi4goij4g",
                R.drawable.onboarding1),
            OnBoardingModel("salam men geldim","gnrgnrgogggi4goij4g", R.drawable.onboarding2),
            OnBoardingModel("salam men geldim","gnrgnrgogggi4goij4g", R.drawable.onboarding3)
        )
        onboardingList.value=list
    }


}