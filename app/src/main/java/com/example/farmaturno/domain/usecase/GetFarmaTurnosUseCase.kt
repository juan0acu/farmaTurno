package com.example.farmaturno.domain.usecase

import com.example.farmaturno.domain.Repository
import javax.inject.Inject

class GetFarmaTurnosUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.getFarmas()

}