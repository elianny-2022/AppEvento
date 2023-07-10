package com.edu.ucne.appevento.ui.evento

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.appevento.data.remote.dto.EventoDto
import com.edu.ucne.appevento.data.repository.EventoRepositoryImp
import com.edu.ucne.appevento.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EventoListState(
    val isLoading: Boolean = false,
    val eventos: List<EventoDto> = emptyList(),
    val error: String = ""
)
data class EventoState(
    val isLoading: Boolean = false,
    val evento: EventoDto ? =  null,
    val error: String = ""
)
@HiltViewModel
class EventoViewModel @Inject constructor(

    private val eventoRepositoryImp: EventoRepositoryImp,

) : ViewModel() {
    var eventoId by mutableStateOf(0)
    var nombreEvento by mutableStateOf("")
    var tipoEvento by mutableStateOf("")
    var fecha by mutableStateOf("" )
    var ubicacion by mutableStateOf("")
    var uiState = MutableStateFlow(EventoListState())
        private set
    var uiStateEvento = MutableStateFlow(EventoState())
        private set

    private fun Limpiar(){
        nombreEvento = ""
        tipoEvento = ""
        ubicacion = ""
        fecha = ""
    }
    fun setEvento(id:Int){
        eventoId = id
        Limpiar()
        eventoRepositoryImp.getEventobyId(eventoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateEvento.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateEvento.update {
                        it.copy(evento = result.data )
                    }
                    nombreEvento = uiStateEvento.value.evento!!.nombreEvento
                    tipoEvento = uiStateEvento.value.evento!!.tipoEvento
                    ubicacion = uiStateEvento.value.evento!!.ubicacion
                    fecha = uiStateEvento.value.evento!!.fecha
                }
                is Resource.Error -> {
                    uiStateEvento.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
    init {
        eventoRepositoryImp.getEvento().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(eventos = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}