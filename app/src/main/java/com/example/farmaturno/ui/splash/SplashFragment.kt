package com.example.farmaturno.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.farmaturno.R
import com.example.farmaturno.ui.splash.states.SplashState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val splashDelay: Long = 3000

    private lateinit var viewModel: SplashFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initUIState()
        viewModel.getFarmasTurnosLocal()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[SplashFragmentViewModel::class.java]
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        SplashState.Loading -> loadinState()
                        is SplashState.Error -> errorState()
                        is SplashState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(state: SplashState.Success) {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, splashDelay)
    }

    private fun errorState() {
        println("No cargo nada")
    }

    private fun loadinState() {
        println("Cargando")
    }


}