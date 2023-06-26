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

    @GET("/list/usario/{correo},{contraseña}")
    suspend fun getUsuarioByLogin(
        @Path("correo") email: String,
        @Path("contraseña") clave: String
    ): UsuarioDto

    @POST("/usuario/save")
    suspend fun postUsuarios(@Body usuarioDto: UsuarioDto)

    @PUT("/usuario/update/{id}")
    suspend fun putUsuarios(@Path("id") id: Int, @Body usuarioDto: UsuarioDto): Response<Unit>


    /* Evento */
    @GET("/evento/list")
    suspend fun getEvento(): List<EventoDto>

    @GET("/eventos/find/{id}")
    suspend fun getEventoById(@Path("id") id: Int): EventoDto


    /* Boletos */
    @GET("/listboleto/boleto")
    suspend fun getBoleto(): List<BoletoDto>

    @GET("/boleto/find/{id}")
    suspend fun getBoletosById(@Path("id") id: Int): BoletoDto


    /* Seccion */
    @GET("/listseccion/seccion")
    suspend fun getSeccion(@Path("id") id: Int): List<SeccionDto>

    @GET("/seccion/find/{id}")
    suspend fun getSeccionById(@Path("id") id: Int): SeccionDto


    /* Silla */
    @GET("/silla/list")
    suspend fun getSilla() : List<SillaDto>
    @GET("/sillas/find/{id}")
    suspend fun getSillaById(@Path("id") id:Int) : SillaDto
}