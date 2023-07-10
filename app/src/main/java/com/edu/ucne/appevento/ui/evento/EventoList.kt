package com.edu.ucne.appevento.ui.evento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.appevento.data.remote.dto.EventoDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventoList(
    onNewEvento: () -> Unit,
    viewModel: EventoViewModel = hiltViewModel(), onEventClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Event Planning",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },

    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            EventListBody(uiState.eventos) {
                onEventClick(it)
            }
        }
    }
}

@Composable
fun EventListBody(eventList: List<EventoDto>, onEventClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(eventList) { event ->
                EventRow(event) {
                    onEventClick(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventRow(event: EventoDto, onEventClick: (Int) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .clickable(onClick = { onEventClick(event.eventoId) })
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    text = event.nombreEvento,
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = event.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = event.tipoEvento,
                    style = MaterialTheme.typography.titleLarge,
                )
              Text(text = event.ubicacion,
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}