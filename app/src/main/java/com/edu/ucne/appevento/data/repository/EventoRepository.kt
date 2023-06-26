package com.edu.ucne.appevento.data.repository

import com.edu.ucne.appevento.data.remote.dto.EventoDto
import com.edu.ucne.appevento.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface EventoRepository {
    fun getEvento(id: Int): Flow<Resource<List<EventoDto>>>
    fun getEventobyId(id: Int): Flow<Resource<EventoDto>>
}