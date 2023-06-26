package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.EventoApi
import com.edu.ucne.appevento.data.remote.dto.BoletoDto
import com.edu.ucne.appevento.data.remote.dto.EventoDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BoletoRepositoryImp @Inject constructor(
    private val api: EventoApi
): BoletoRepository {
    override fun getBoleto(id: Int): Flow<Resource<List<BoletoDto>>> = flow {
        try {
            emit(Resource.Loading())

            val boleto = api.getBoleto()

            emit(Resource.Success(boleto))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    override fun getBoletobyId(id: Int): Flow<Resource<BoletoDto>> = flow {
        try {
            emit(Resource.Loading())

            val boleto = api.getBoletosById(id)

            emit(Resource.Success(boleto))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}