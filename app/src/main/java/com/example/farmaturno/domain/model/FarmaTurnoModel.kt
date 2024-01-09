package com.example.farmaturno.domain.model


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
)
