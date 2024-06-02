package com.babak.newsapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babak.newsapp.AuthViewModel
import com.babak.newsapp.databinding.FragmentRegisterBinding
import com.babak.newsapp.utils.gone
import com.babak.newsapp.utils.inVisible
import com.babak.newsapp.utils.visible


class RegisterFragment : Fragment() {
    var _binding: FragmentRegisterBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observedData()
        binding.toLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        binding.registerBtn.setOnClickListener {
            registerUser()
        }
    }

    private fun observedData() {
        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Ugurlu qeydiyyat", Toast.LENGTH_LONG).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()

        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar2.visible()
                binding.registerBtn.inVisible()
            }else{
                binding.progressBar2.gone()
                binding.registerBtn.visible()
            }
        }
    }

    private fun registerUser() {
        val email = binding.registerUsernameText.text.toString().trim()
        val password = binding.registerPasswordText.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            binding.usernameInput.error = null
            binding.passwordInput.error = null
            viewModel.register(email, password)
        } else if (email.isEmpty() && password.isEmpty()) {
            binding.usernameInput.error = "Email bos buraxilib"
            binding.passwordInput.error = "Parol bos buraxilib"
        } else if (email.isEmpty() && password.isNotEmpty()) {
            binding.passwordInput.error = null
            binding.usernameInput.error = "Email bos buraxilib"
        } else if (email.isNotEmpty() && password.isEmpty()) {
            binding.passwordInput.error = "Parol bos buraxilib"
            binding.usernameInput.error = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}