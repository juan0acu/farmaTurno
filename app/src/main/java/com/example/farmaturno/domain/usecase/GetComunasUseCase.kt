package com.example.farmaturno.domain.usecase

import com.example.farmaturno.domain.Repository
import javax.inject.Inject

class GetComunasUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(query: String): List<String>{
        return repository.getCiudadesAutoComplete(query)
    }
}