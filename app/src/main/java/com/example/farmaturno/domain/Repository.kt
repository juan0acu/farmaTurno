package com.example.farmaturno.domain

import com.example.farmaturno.domain.entity.FarmaciaActualizada
import com.example.farmaturno.domain.entity.FarmaciasTurno
import com.example.farmaturno.domain.model.FarmaTurnoModel

interface Repository {

    suspend fun getFarmas(): List<FarmaTurnoModel>?

    suspend fun getFarmaciasLocal(): List<FarmaciaActualizada>
    suspend fun updateFarmacias(farmaciasTurno: List<FarmaciasTurno>, nuevaFecha:Long)
}