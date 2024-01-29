package com.example.farmaturno.domain.usecase

import com.example.farmaturno.domain.Repository
import com.example.farmaturno.domain.model.FarmaTurnoModel
import javax.inject.Inject

class GetFarmaciasComuna @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke (query: String): List<FarmaTurnoModel>{
        return repository.getFarmaciasComuna(query)
    }
}