package com.example.farmaturno.domain.usecase

import com.example.farmaturno.domain.Repository
import javax.inject.Inject

class GetFarmaciasLocalUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.getFarmaciasLocal()
}