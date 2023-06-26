package com.edu.ucne.appevento.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.appevento.data.repository.UsuarioRepositoryImp
import com.edu.ucne.appevento.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class Login(
    val correo: String = "",
    val contraseña: String ="",
    val id: Int = 0
)

@HiltViewModel
class ViewModelLogin @Inject constructor(
    private val loginRepos: UsuarioRepositoryImp

): ViewModel(){

    var uiState = MutableStateFlow(Login())

    fun setLogin() {
        loginRepos.getUsuarioByLogin(correo = uiState.value.correo, contraseña = uiState.value.contraseña).onEach { resul ->
            when (resul) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (resul.data != null) {
                        uiState.update {
                            it.copy(
                                id = resul.data.usuarioId ?: 0
                            )
                        }
                    }
                }
                is Resource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
        if (uiState.value.id == 0){
            uiState.update {
                it.copy(contraseña = "")
            }
        }

    }

    fun setClave(clave: String) {
        uiState.update {
            it.copy(contraseña = clave)
        }
    }
    fun setEmail(correo: String) {
        uiState.update {
            it.copy(correo = correo)
        }
    }
}