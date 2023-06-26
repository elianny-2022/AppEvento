package com.edu.ucne.appevento.data.remote.dto

import java.util.*

data class EventoDto(
    val eventoId: Int=0,
    val nombreEvento: String = "",
    val tipoEvento: String = "",
    val fecha: Date,
    val ubicacion: String="",
    val asientoDisponible: Double,
)