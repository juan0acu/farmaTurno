package com.example.farmaturno.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.farmaturno.R
import com.example.farmaturno.data.network.response.FarmaDataResponse
import com.example.farmaturno.data.network.retrofit.FarmaApiService
import com.example.farmaturno.domain.usecase.GetComunasUseCase
import com.example.farmaturno.domain.usecase.GetFarmaTurnosUseCase
import com.example.farmaturno.domain.usecase.GetFarmaciasComuna
import com.example.farmaturno.ui.home.states.HomeState
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class HomePrincipalViewModel @Inject constructor(
    private val getFarmaTurnosUseCase: GetFarmaTurnosUseCase,
    private val getComunasUseCase: GetComunasUseCase,
    private val getFarmaciasComuna: GetFarmaciasComuna
) : ViewModel() {
    private var _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    fun getFarmasTurnos(){
        viewModelScope.launch {
            _state.value = HomeState.Loading
            val result = withContext(Dispatchers.IO){
                getFarmaTurnosUseCase.invoke()
            }
            if (result.isNotEmpty()){
                _state.value = HomeState.Success(result)
            }else{
                _state.value = HomeState.Error("Error de Servicio")
                FirebaseCrashlytics.getInstance().log("Error de Servicio")
                FirebaseCrashlytics.getInstance().recordException(Throwable("Error de Servicio"))
            }
        }
    }

    fun getBuscarComunas(query: String){
        viewModelScope.launch {
            _state.value = HomeState.Loading

            val result = withContext(Dispatchers.IO){
                getComunasUseCase.invoke(query)
            }
            if (result.isNotEmpty()){
                _state.value = HomeState.SuccessComunas(result)
            }else {
                _state.value = HomeState.Error("No se encuentra la ciudad indicada")
            }
        }
    }

    fun getFarmaciasComunas(query: String){
        viewModelScope.launch {
            _state.value = HomeState.Loading

            val result = withContext(Dispatchers.IO){
                getFarmaciasComuna.invoke(query)
            }
            if (result.isNotEmpty()){
                println("Exitoso con las farmacias de comuna")
                println(HomeState.Success(result))
                _state.value = HomeState.Success(result)
            }else{
                println("Farmacias no Encontrada en esa comuna")
                _state.value = HomeState.Error("Farmacias no Encontrada en esa comuna")
            }
        }
    }
}