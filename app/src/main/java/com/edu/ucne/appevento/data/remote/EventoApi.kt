package com.edu.ucne.appevento.data.remote

import com.edu.ucne.appevento.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface EventoApi {
    /* Usuario */
    @GET("/list/usuario")
    suspend fun getUsuario(): List<UsuarioDto>

    @GET("/usuario/usuarios/{id}")
    suspend fun getUsuariosById(@Path("id") id: Int): UsuarioDto

    @POST("/usuario/save")
    suspend fun postUsuarios(@Body usuarioDto: UsuarioDto)

    @PUT("/usuario/update/{id}")
    suspend fun putUsuarios(@Path("id") id: Int, @Body usuarioDto: UsuarioDto): Response<Unit>


    /* Evento */
    @GET("eventos/evento/list")
    suspend fun getEvento(): List<EventoDto>

    @GET("eventos/eventos/find/{id}")
    suspend fun getEventoById(@Path("id") id: Int): EventoDto


    /* Boletos */
    @GET("/boletos/listboleto/boleto")
    suspend fun getBoleto(): List<BoletoDto>

    @GET("/boletos/boleto/find/{id}")
    suspend fun getBoletosById(@Path("id") id: Int): BoletoDto


}