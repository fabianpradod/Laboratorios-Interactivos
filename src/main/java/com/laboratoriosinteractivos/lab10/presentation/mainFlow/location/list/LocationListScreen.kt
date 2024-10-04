package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.list


import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laboratoriosinteractivos.lab10.data.model.Location
import com.laboratoriosinteractivos.lab10.data.source.LocationDb
import com.laboratoriosinteractivos.lab10.ui.theme.RickAndMortyTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment


@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingLayout(onLoadingClick = { viewModel.triggerError() })
        }

        uiState.hasError -> {
            ErrorLayout(onRetryClick = { viewModel.retryGetLocations() })
        }

        uiState.data != null -> {
            val locationDb = LocationDb()
            val locations = locationDb.getAllLocations()
            LocationListScreen(
                locations = locations,
                onLocationClick = onLocationClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun LoadingLayout(onLoadingClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onLoadingClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Text(text = "Loading Locations", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ErrorLayout(onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Error al obtener el listado de lugares", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
private fun LocationListScreen(
    locations: List<Location>,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(locations) { item ->
            LocationItem(
                location = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLocationClick(item.id) }
            )
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
    ) {
        Text(text = location.name)
        Text(
            text = location.type,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationListScreen() {
    RickAndMortyTheme() {
        Surface {
            val db = LocationDb()
            LocationListScreen(
                locations = db.getAllLocations().take(6),
                onLocationClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}