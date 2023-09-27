package com.example.farmaturno.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmaturno.R
import com.example.farmaturno.data.model.FarmaDataResponse
import com.example.farmaturno.data.retrofit.FarmaApiService
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class HomePrincipalViewModel @Inject constructor(
    private val apiService: FarmaApiService,
    app: Application
) : ViewModel() {
    private val placesClient: PlacesClient = Places.createClient(app)

    private val _farmaciasLiveData = MutableLiveData<List<FarmaDataResponse>>()

    val farmaciasLiveData: LiveData<List<FarmaDataResponse>>
        get() = _farmaciasLiveData

    private val _suggestionsLiveData = MutableLiveData<List<AutocompletePrediction>?>()
    val suggestionsLiveData: MutableLiveData<List<AutocompletePrediction>?>
        get() = _suggestionsLiveData

    fun initialLocalTurnos() {
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

   /* fun getAutocompletePredictions(query: String?): Task<FindAutocompletePredictionsResponse> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        return placesClient.findAutocompletePredictions(request)
    }*/

    fun getAutocompletePredictions(query: String?): Task<FindAutocompletePredictionsResponse> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setCountries("CL")
            .setTypeFilter(TypeFilter.CITIES)
            .build()

       return placesClient.findAutocompletePredictions(request)
            .addOnCompleteListener { autocompletePredictions ->
                if (autocompletePredictions.isSuccessful) {
                    val predictions: List<AutocompletePrediction>? =
                        autocompletePredictions.result?.autocompletePredictions
                    if (predictions != null) {
                        // Emitir las sugerencias al LiveData
                        _suggestionsLiveData.postValue(predictions)
                    }
                } else {
                    val exception: ApiException? =
                        autocompletePredictions.exception as ApiException?
                    exception?.printStackTrace()
                }
            }
    }
}