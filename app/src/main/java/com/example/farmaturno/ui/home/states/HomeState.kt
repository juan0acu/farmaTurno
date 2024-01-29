package com.example.farmaturno.ui.home.states

import com.example.farmaturno.domain.model.FarmaTurnoModel

sealed class HomeState {
    object Loading : HomeState()
    data class Error(val error: String) : HomeState()
    data class Success(val listFarma: List<FarmaTurnoModel>?) : HomeState()
}