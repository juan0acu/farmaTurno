package com.example.farmaturno.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePrincipalFragment : Fragment() {

    private lateinit var binding: FragmentHomePrincipalBinding
    private lateinit var viewModel: HomePrincipalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePrincipalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomePrincipalViewModel::class.java]
    }

}