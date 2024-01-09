package com.example.farmaturno.data

import android.util.Log
import com.example.farmaturno.data.network.retrofit.FarmaApiService
import com.example.farmaturno.domain.Repository
import com.example.farmaturno.domain.model.FarmaTurnoModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

class FarmaRepositoryImpl @Inject constructor(private val apiService: FarmaApiService) :
    Repository {

    override suspend fun getFarmas(): List<FarmaTurnoModel>? {
        return runCatching { apiService.getFarmas().map { it.toDomain() } }
            .onFailure {
                Log.i("Error", "Ha ocurrido un error ${it.message}")
                it.message?.let { it1 -> FirebaseCrashlytics.getInstance().log(it1) }
                FirebaseCrashlytics.getInstance().recordException(Throwable(it))
            }.getOrNull()
    }
}