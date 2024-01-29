package com.example.farmaturno.data

import android.util.Log
import com.example.farmaturno.data.local.database.dao.ActualizacionDao
import com.example.farmaturno.data.local.database.dao.FarmaciaDao
import com.example.farmaturno.data.local.database.entity.ActualizacionTablas
import com.example.farmaturno.data.network.retrofit.FarmaApiService
import com.example.farmaturno.domain.Repository
import com.example.farmaturno.domain.entity.FarmaciaActualizada
import com.example.farmaturno.domain.entity.FarmaciasTurno
import com.example.farmaturno.domain.model.FarmaTurnoModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FarmaRepositoryImpl @Inject constructor(
    private val apiService: FarmaApiService,
    private val farmaciaDao: FarmaciaDao,
    private val actualizacionDao: ActualizacionDao
) :
    Repository {

    override suspend fun getFarmas(): List<FarmaTurnoModel>? {
        return runCatching { apiService.getFarmas().map { it.toDomain() } }
            .onFailure {
                Log.i("Error", "Ha ocurrido un error ${it.message}")
                it.message?.let { it1 -> FirebaseCrashlytics.getInstance().log(it1) }
                FirebaseCrashlytics.getInstance().recordException(Throwable(it))
            }.getOrNull()
    }

    override suspend fun getFarmaciasLocal(): List<FarmaciaActualizada> {
        val localFarmacia = farmaciaDao.getAllFarmacias()
        return if (localFarmacia.isNotEmpty()) {
            farmaciaDao.deleteAll()
            val farmaciasTurno = apiService.getFarmas()?.map { it.toFarmaciasTurno()}
            farmaciasTurno?.let {
                println("Ingreso a la base de de datos y borro la vieja")
                val localEntities = it.map { farmacia -> farmacia.toLocalEntity() }
                farmaciaDao.insertAllFarmacias(localEntities)
                println("se supone que guardo las nuevas farmacias actualizadas")
                val formatoHoraFecha = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    .format(Date(System.currentTimeMillis()))
                actualizacionDao.updateActualización(ActualizacionTablas(1,formatoHoraFecha))
                it.map { farmacia -> FarmaciaActualizada(farmacia, actualizacionDao.getActualizar())  }
            }?: emptyList()
        }else{
            val farmaciasTurno = apiService.getFarmas()?.map { it.toFarmaciasTurno()}
            farmaciasTurno?.let {
                println("Ingreso despues que fue al servicio")
                val localEntities = it.map { farmacia -> farmacia.toLocalEntity() }
                farmaciaDao.insertAllFarmacias(localEntities)
                println("se supone que guardo las farmacias")
                val formatoHoraFecha = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    .format(Date(System.currentTimeMillis()))
                actualizacionDao.updateActualización(ActualizacionTablas(1,formatoHoraFecha))
                it.map { farmacia -> FarmaciaActualizada(farmacia, actualizacionDao.getActualizar())  }
            }?: emptyList()
        }
        }

    override suspend fun updateFarmacias(farmaciasTurno: List<FarmaciasTurno>, nuevaFecha: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getFarmasData(): List<FarmaTurnoModel> {
        val localFarmaciasTurno = farmaciaDao.getAllFarmaciasModels()
        return if (localFarmaciasTurno.isNotEmpty()){
            localFarmaciasTurno
        }else{
            emptyList()
        }
    }
}
