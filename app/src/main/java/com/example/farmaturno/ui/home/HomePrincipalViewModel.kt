package com.example.farmaturno.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmaturno.R
import com.example.farmaturno.data.model.FarmaDataResponse
import com.example.farmaturno.data.retrofit.FarmaApiService
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class HomePrincipalViewModel @Inject constructor(
    private val apiService: FarmaApiService
): ViewModel() {
    private val _farmaciasLiveData = MutableLiveData<List<FarmaDataResponse>>()

    val farmaciasLiveData: LiveData<List<FarmaDataResponse>>
        get() = _farmaciasLiveData

    fun initialLocalTurnos(){
        val call = apiService.getFarmas()

        call.enqueue(object : Callback<List<FarmaDataResponse>> {
            override fun onResponse(
                call: Call<List<FarmaDataResponse>>,
                response: Response<List<FarmaDataResponse>>
            ) {
                if (response.isSuccessful) {
                    _farmaciasLiveData.value = response.body()
                } else {
                    val errorMessage = R.string.error_api.toString()
                    FirebaseCrashlytics.getInstance().log(errorMessage)
                    FirebaseCrashlytics.getInstance().recordException(Throwable(errorMessage))
                }
            }

            override fun onFailure(call: Call<List<FarmaDataResponse>>, t: Throwable) {
                val errorMessage = "Error en la solicitud a la API: ${t.message}"
                FirebaseCrashlytics.getInstance().log(errorMessage)
                FirebaseCrashlytics.getInstance().recordException(Throwable(errorMessage))
            }
        })
    }
}