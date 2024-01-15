package com.example.farmaturno.domain.entity

import com.example.farmaturno.data.local.database.entity.ActualizacionTablas


data class FarmaciasTurno(
    var id: Int,
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
){
    fun toLocalEntity(): com.example.farmaturno.data.local.database.entity.FarmaciasTurno {
        return com.example.farmaturno.data.local.database.entity.FarmaciasTurno(
            id = this.id,
            fecha_actual = this.fecha_actual,
            id_farmacia = this.id_farmacia,
            id_region = this.id_region,
            id_comuna = this.id_comuna,
            id_localidad = this.id_localidad,
            farmacia_name = this.farmacia_name,
            comuna_name = this.comuna_name,
            localidad_name = this.localidad_name,
            farmacia_direccion = this.farmacia_direccion,
            farmacia_apertura = this.farmacia_apertura,
            farmacia_cierre = this.farmacia_cierre,
            farmacia_telefono = this.farmacia_telefono,
            farmacia_latitud = this.farmacia_latitud,
            farmacia_longitud = this.farmacia_longitud,
            dia_funcionamiento = this.dia_funcionamiento
        )
    }
}

data class Actualizacion(
    val id: Int,
    val fechaActualizacion: Long
)

data class FarmaciaActualizada(
    val farmacia: FarmaciasTurno,
    val actualizacion: ActualizacionTablas
)
