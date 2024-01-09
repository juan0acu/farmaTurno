package com.example.farmaturno.domain

import com.example.farmaturno.domain.model.FarmaTurnoModel

interface Repository {

    suspend fun getFarmas(): List<FarmaTurnoModel>?
}