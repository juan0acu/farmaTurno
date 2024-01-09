package com.example.farmaturno.data.network.retrofit

import com.example.farmaturno.data.network.response.FarmaDataResponse
import retrofit2.http.GET

interface FarmaApiService {

    @GET("getLocalesTurnos.php")
    suspend fun getFarmas(): List<FarmaDataResponse>
}