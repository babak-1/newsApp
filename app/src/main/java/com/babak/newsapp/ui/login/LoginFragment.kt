package com.babak.newsapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babak.newsapp.AuthViewModel
import com.babak.newsapp.databinding.FragmentLoginBinding
import com.babak.newsapp.utils.gone
import com.babak.newsapp.utils.inVisible
import com.babak.newsapp.utils.visible


class LoginFragment : Fragment() {
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.toRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.loginButton.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser(){
        val email = binding.usernameEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        if(email.isNotEmpty()&&password.isNotEmpty()){
            binding.usernameInputLayout.error=null
            binding.passwordInputLayout.error=null
            viewModel.login(email, password)
        }else if (email.isEmpty()&&password.isEmpty()){
            binding.usernameInputLayout.error="Email bos buraxilib"
            binding.passwordInputLayout.error="Parol bos buraxilib"
        }else if (email.isEmpty()&&password.isNotEmpty()){
            binding.passwordInputLayout.error=null
            binding.usernameInputLayout.error="Email bos buraxilib"
        }else if(email.isNotEmpty()&&password.isEmpty()){
            binding.passwordInputLayout.error="Parol bos buraxilib"
            binding.usernameInputLayout.error=null
        }
    }

    private fun observeData(){
        viewModel.isLogin.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context,"Ugurlu giris",Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }else{
                Toast.makeText(context,"Giris melumatlari duzgun deyil",Toast.LENGTH_LONG).show()

            }
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visible()
                binding.loginButton.inVisible()
            } else{
                binding.progressBar.gone()
                binding.loginButton.visible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}