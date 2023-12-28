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

    private val _searchedLocalidad = MutableLiveData<String>()
    val searchedLocalidad: LiveData<String>
        get() = _searchedLocalidad

    fun initialLocalTurnos() {
        val call = apiService.getFarmas()

        call.enqueue(object : Callback<List<FarmaDataResponse>> {
            override fun onResponse(
                call: Call<List<FarmaDataResponse>>,
                response: Response<List<FarmaDataResponse>>
            ) {
                if (response.isSuccessful) {
                   // _farmaciasLiveData.value = response.body()
                    val searchedLocalidad = _searchedLocalidad.value
                    val farmaciasSearch = response.body()?.filter { farmacia ->
                        searchedLocalidad.isNullOrEmpty() || farmacia.localidad_name.equals(searchedLocalidad, ignoreCase = true)
                    }
                    _farmaciasLiveData.value = farmaciasSearch ?: emptyList()
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

    fun getAutocompletePredictions(query: String?): Task<FindAutocompletePredictionsResponse> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setCountry("CL") // CL es el código ISO 3166-1 para Chile
            .setTypeFilter(TypeFilter.CITIES)
            .build()

        return placesClient.findAutocompletePredictions(request)
            .addOnCompleteListener { autocompletePredictions ->
                if (autocompletePredictions.isSuccessful) {
                    val predictions: List<AutocompletePrediction>? =
                        autocompletePredictions.result?.autocompletePredictions
                    if (predictions != null) {
                        // Procesar las sugerencias para eliminar el país
                        val cityPredictions = predictions.map { prediction ->
                            val fullText = prediction.getFullText(null).toString()
                            val cityName =
                                fullText.substringBefore(",") // Obtener solo el nombre de la ciudad
                            val placeId = prediction.placeId
                            val primaryText = prediction.getPrimaryText(null).toString()
                            val secondaryText = prediction.getSecondaryText(null).toString()

                            // Crear una instancia de AutocompletePrediction.Builder
                            val predictionBuilder = AutocompletePrediction.builder(placeId)

                            // Establecer los valores en el builder
                            predictionBuilder.setFullText(cityName)
                            predictionBuilder.setPrimaryText(primaryText)
                            predictionBuilder.setSecondaryText(secondaryText)

                            // Construir la instancia de AutocompletePrediction
                            predictionBuilder.build()
                        }
                        // Emitir las sugerencias de ciudades al LiveData
                        _suggestionsLiveData.postValue(cityPredictions)
                    }
                } else {
                    val exception: ApiException? =
                        autocompletePredictions.exception as ApiException?
                    exception?.printStackTrace()
                }
            }

    }

    fun setSearchedLocalidad(localidad: String) {
        _searchedLocalidad.value = localidad
        initialLocalTurnos()
    }
}