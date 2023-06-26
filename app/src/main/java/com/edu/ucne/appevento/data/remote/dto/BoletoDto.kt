package com.edu.ucne.appevento.data.remote.dto

import java.util.*

data class BoletoDto (
    val boletoId: Int =0,
    val precio: Double,
    val fecha: Date,
    val cantidadBoletos: Double,
    )