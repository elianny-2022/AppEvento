package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.EventoApi
import com.edu.ucne.appevento.data.remote.dto.UsuarioDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsuarioRepositoryImp @Inject constructor(
    private val api:EventoApi
): UsuarioRepository {

    override fun getUsuario(id: Int): Flow<Resource<List<UsuarioDto>>> = flow {
        try {
            emit(Resource.Loading())

            val usuario = api.getUsuario()

            emit(Resource.Success(usuario))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override fun getUsuarioByLogin(correo: String, contraseña: String): Flow<Resource<UsuarioDto>> = flow{
        try {
            emit(Resource.Loading())

            val usuario = api.getUsuarioByLogin(correo, contraseña)

            emit (Resource.Success(usuario))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getUsuarioById(id: Int): Flow<Resource<UsuarioDto>> = flow {
        try {
            emit(Resource.Loading())

            val usuario = api.getUsuariosById(id)

            emit(Resource.Success(usuario))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override suspend fun putUsuario(id: Int, usuarioDto: UsuarioDto) {
        api.putUsuarios(id, usuarioDto)
    }

}