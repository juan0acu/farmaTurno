package com.example.farmaturno.data.network.response

import com.example.farmaturno.domain.entity.FarmaciasTurno
import com.example.farmaturno.domain.model.FarmaTurnoModel
import com.google.gson.annotations.SerializedName

data class FarmaDataResponse(
    @SerializedName("fecha") val fecha_actual: String,
    @SerializedName("local_id") val id_farmacia: String,
    @SerializedName("fk_region") val id_region: String,
    @SerializedName("fk_comuna") val id_comuna: String,
    @SerializedName("fk_localidad") val id_localidad: String,
    @SerializedName("local_nombre") val farmacia_name: String,
    @SerializedName("comuna_nombre") val comuna_name: String,
    @SerializedName("localidad_nombre") val localidad_name: String,
    @SerializedName("local_direccion") val farmacia_direccion: String,
    @SerializedName("funcionamiento_hora_apertura") val farmacia_apertura: String,
    @SerializedName("funcionamiento_hora_cierre") val farmacia_cierre: String,
    @SerializedName("local_telefono") val farmacia_telefono: String,
    @SerializedName("local_lat") val farmacia_latitud: String,
    @SerializedName("local_lng") val farmacia_longitud: String,
    @SerializedName("funcionamiento_dia") val dia_funcionamiento: String
) {
    fun toDomain(): FarmaTurnoModel {
        return FarmaTurnoModel(
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
        )
    }

    companion object{
        private var currentId = 0
    }

    fun toFarmaciasTurno(): FarmaciasTurno {
        return FarmaciasTurno(
            id = currentId++,
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
        )
    }
    /*fun toDomain(): FarmaTurnoModel {
        return FarmaTurnoModel(
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
        )
    }*/
}
