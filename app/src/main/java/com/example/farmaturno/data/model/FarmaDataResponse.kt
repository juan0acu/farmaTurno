package com.example.farmaturno.data.model

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
    @SerializedName("funcionamiento_dia") val dia_funcionamiento: String,
)
