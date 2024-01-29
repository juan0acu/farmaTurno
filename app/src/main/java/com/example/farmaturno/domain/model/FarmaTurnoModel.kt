package com.example.farmaturno.domain.model

import com.example.farmaturno.domain.entity.FarmaciasTurno


data class FarmaTurnoModel(
    val fecha_actual: String,
    val id_farmacia: String,
    val id_region: String,
    val id_comuna: String,
    val id_localidad: String,
    val farmacia_name: String,
    val comuna_name: String,
    val localidad_name: String,
    val farmacia_direccion: String,
    val farmacia_apertura: String,
    val farmacia_cierre: String,
    val farmacia_telefono: String,
    val farmacia_latitud: String,
    val farmacia_longitud: String,
    val dia_funcionamiento: String
) {
    fun toFarmaciasTurno(): FarmaciasTurno {
        return FarmaciasTurno(
            id = 0,
            fecha_actual = fecha_actual,
            id_farmacia = id_farmacia,
            id_region = id_region,
            id_comuna = id_comuna,
            id_localidad = id_localidad,
            farmacia_name = farmacia_name,
            comuna_name = comuna_name,
            localidad_name = localidad_name,
            farmacia_direccion = farmacia_direccion,
            farmacia_apertura = farmacia_apertura,
            farmacia_cierre = farmacia_cierre,
            farmacia_telefono = farmacia_telefono,
            farmacia_latitud = farmacia_latitud,
            farmacia_longitud = farmacia_longitud,
            dia_funcionamiento = dia_funcionamiento
        ).apply {
            id += 1
        }
    }
}
