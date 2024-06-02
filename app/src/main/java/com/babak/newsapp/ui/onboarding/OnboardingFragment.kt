package com.babak.newsapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.babak.newsapp.R
import com.babak.newsapp.databinding.FragmentOnboardingBinding
import com.babak.newsapp.model.OnBoardingModel
import com.babak.newsapp.utils.gone
import com.babak.newsapp.utils.visible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.OnChangedCallback

class OnboardingFragment : Fragment() {
    private var _binding:FragmentOnboardingBinding ?= null
    private val binding get() = _binding!!
    private val onboardingAdapter=OnboardingAdapter()
    private val viewModel by viewModels<OnboardongViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentOnboardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerOnboarding.adapter=onboardingAdapter
        binding.springDotsIndicator.attachTo(binding.viewPagerOnboarding)
        observData()
        changePager()
    }


    private fun observData(){
        viewModel.onboardingList.observe(viewLifecycleOwner){
            onboardingAdapter.updateList(it)
        }
    }

    private fun changePager(){
        binding.viewPagerOnboarding.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handlePageChange(position)
            }
        })

        binding.buttonNext.setOnClickListener {
            if(binding.viewPagerOnboarding.currentItem==2){
                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            }
            binding.viewPagerOnboarding.currentItem ++

            binding.buttonBack.setOnClickListener {
                binding.viewPagerOnboarding.currentItem --
            }
        }
    }

    private fun handlePageChange(position:Int){
        when(position){
           1->{
               binding.buttonBack.visible()
           }
           2->{
               binding.buttonBack.visible()
               binding.buttonNext.text="Get Start"
           }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}