package com.example.farmaturno.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmaturno.domain.usecase.GetFarmaciasLocalUseCase
import com.example.farmaturno.ui.splash.states.SplashState
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val getFarmaciasLocalUseCase: GetFarmaciasLocalUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val  state: StateFlow <SplashState> = _state

    fun getFarmasTurnosLocal(){
        viewModelScope.launch {
            _state.value = SplashState.Loading
            val result = withContext(Dispatchers.IO){
                getFarmaciasLocalUseCase.invoke()
            }
            if (result!=null){
                _state.value = SplashState.Success(result)
            }else{
                _state.value= SplashState.Error("No hizo nada")
                FirebaseCrashlytics.getInstance().log("Error de Servicio")
                FirebaseCrashlytics.getInstance().recordException(Throwable("Error de Servicio"))
            }
        }
    }
}