package com.example.farmaturno.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.farmaturno.R
import com.example.farmaturno.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.cardCorreo.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_homePrincipalFragment)
        }
        binding.cardFacebook.setOnClickListener {
                throw RuntimeException("Test Crash") // Force a crash
        }

        binding.cardGmail.setOnClickListener {
            //TODO
        }
        binding.btnRegistrar.setOnClickListener {
        //TODO
        }
    }
}