package com.edu.ucne.appevento.data.remote.dto

import com.squareup.moshi.JsonClass
import java.util.*
@JsonClass(generateAdapter = true  )
data class EventoDto(
    val eventoId: Int,
    val nombreEvento: String = "",
    val tipoEvento: String = "",
    val fecha: String,
    val ubicacion: String="",
    val descripcion: String
)