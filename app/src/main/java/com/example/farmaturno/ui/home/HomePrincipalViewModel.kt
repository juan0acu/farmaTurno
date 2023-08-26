package com.example.farmaturno.ui.home

import androidx.lifecycle.ViewModel
import com.example.farmaturno.data.retrofit.FarmaApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePrincipalViewModel @Inject constructor(
    private val apiService: FarmaApiService
): ViewModel() {

    fun initialLocalTurnos(){
        val call = apiService.getFarmas()
    }
}