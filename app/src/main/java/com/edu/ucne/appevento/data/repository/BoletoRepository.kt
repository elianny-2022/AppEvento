package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.dto.BoletoDto
import com.edu.ucne.appevento.data.remote.dto.EventoDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface BoletoRepository {
    fun getBoleto(id: Int): Flow<Resource<List<BoletoDto>>>
    fun getBoletobyId(id: Int): Flow<Resource<BoletoDto>>
}