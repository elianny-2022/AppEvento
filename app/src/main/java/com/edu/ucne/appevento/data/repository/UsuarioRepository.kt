package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.dto.UsuarioDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    fun getUsuario(id: Int): Flow<Resource<List<UsuarioDto>>>
    fun getUsuarioById(id: Int): Flow<Resource<UsuarioDto>>
    suspend fun putUsuario(id: Int, usuarioDto: UsuarioDto)
    fun getUsuarioByLogin(correo: String, contraseña:String ): Flow<Resource<UsuarioDto>>
}