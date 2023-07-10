package com.edu.ucne.appevento.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true  )
 data class UsuarioDto (

     val usuarioId:Int?=0,
     val nombre:String="",
     val direccion: String="",
     val correo: String="",
     val contraseña: String="",
     val historial: String=""
 )