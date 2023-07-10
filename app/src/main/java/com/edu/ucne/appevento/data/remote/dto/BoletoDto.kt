package com.edu.ucne.appevento.data.remote.dto

import com.squareup.moshi.JsonClass
import java.util.*
@JsonClass(generateAdapter = true  )
data class BoletoDto (
    val boletoId: Int =0,
    val precio: Double,
    val fecha: Date,
    val cantidadBoletos: Double,
    )