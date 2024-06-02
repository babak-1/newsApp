package com.babak.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.babak.newsapp.R
import com.babak.newsapp.databinding.FragmentHomeBinding
import com.babak.newsapp.utils.gone
import com.babak.newsapp.utils.visible

class HomeFragment : Fragment() {
    var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    var adapter = HomeCardAdapter()
    val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNews.adapter=adapter
        observData()
        viewModel.requestAllNews()

    }

    fun observData(){
        viewModel.allNews.observe(viewLifecycleOwner){
            adapter.updateList(it.articles)
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar3.visible()
            }else{
                binding.progressBar3.gone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}