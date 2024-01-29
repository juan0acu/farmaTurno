package com.example.farmaturno.ui.splash.states

import com.example.farmaturno.domain.entity.FarmaciaActualizada

sealed class SplashState {
    object Loading : SplashState()
    data class Error(val error: String) : SplashState()
    data class Success(val listFarma: List<FarmaciaActualizada>):SplashState()
}