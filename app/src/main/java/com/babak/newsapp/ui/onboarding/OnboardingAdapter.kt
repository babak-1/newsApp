package com.babak.newsapp.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babak.newsapp.databinding.ItemOnboardingBinding
import com.babak.newsapp.model.OnBoardingModel

class OnboardingAdapter:RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    private val list= arrayListOf<OnBoardingModel>()

    inner class OnboardingViewHolder(val itemOnboardingBinding: ItemOnboardingBinding):RecyclerView.ViewHolder(itemOnboardingBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
      val view = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnboardingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val item =list[position]
        holder.itemOnboardingBinding.onboardingData=item
    }

    fun updateList(newList:List<OnBoardingModel>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


}