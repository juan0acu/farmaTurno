package com.example.farmaturno.data.retrofit

import com.example.farmaturno.data.model.FarmaDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface FarmaApiService {

    @GET("/getLocalesTurnos.php")
    fun getFarmas(): Call<List<FarmaDataResponse>>
}