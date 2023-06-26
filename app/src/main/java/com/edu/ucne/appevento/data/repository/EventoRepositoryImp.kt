package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.EventoApi
import com.edu.ucne.appevento.data.remote.dto.EventoDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventoRepositoryImp @Inject constructor(
    private val api: EventoApi
): EventoRepository {

    override fun getEvento(id: Int): Flow<Resource<List<EventoDto>>> = flow {
        try {
            emit(Resource.Loading())

            val cita = api.getEvento()

            emit(Resource.Success(cita))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getEventobyId(id: Int): Flow<Resource<EventoDto>> = flow {
        try {
            emit(Resource.Loading())

            val evento = api.getEventoById(id)

            emit(Resource.Success(evento))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}