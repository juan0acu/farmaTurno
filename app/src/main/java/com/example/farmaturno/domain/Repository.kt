package com.example.farmaturno.domain

import androidx.room.Query
import com.example.farmaturno.domain.entity.FarmaciaActualizada
import com.example.farmaturno.domain.entity.FarmaciasTurno
import com.example.farmaturno.domain.model.FarmaTurnoModel

interface Repository {

    suspend fun getFarmas(): List<FarmaTurnoModel>?

    suspend fun getFarmaciasLocal(): List<FarmaciaActualizada>
    suspend fun updateFarmacias(farmaciasTurno: List<FarmaciasTurno>, nuevaFecha:Long)

    suspend fun getFarmasData(): List<FarmaTurnoModel>

    suspend fun getCiudadesAutoComplete(query: String): List<String>

    suspend fun getFarmaciasComuna(query: String):List<FarmaTurnoModel>
}