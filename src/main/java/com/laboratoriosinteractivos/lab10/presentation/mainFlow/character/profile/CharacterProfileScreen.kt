package com.laboratoriosinteractivos.lab10.presentation.mainFlow.character.profile


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laboratoriosinteractivos.lab10.data.source.CharacterDb
import com.laboratoriosinteractivos.lab10.ui.theme.RickAndMortyTheme
import com.laboratoriosinteractivos.lab10.data.model.Character
import androidx.compose.runtime.getValue


@Composable
fun CharacterProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: CharacterProfileViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingLayout(onLoadingClick = { viewModel.triggerError() })
        }

        uiState.hasError -> {
            ErrorLayout(onRetryClick = { viewModel.retryGetCharacter() })
        }

        uiState.data != null -> {
            CharacterProfileScreen(
                character = uiState.data!!,
                onNavigateBack = onNavigateBack,
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
            Text(text = "Loading Character Profile", style = MaterialTheme.typography.bodyMedium)
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
            Text(text = "Error al obtener el perfil del personaje", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterProfileScreen(
    character: Character,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Character Detail")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "Person",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            CharacterProfilePropItem(
                title = "Species:",
                value = character.species,
                modifier = Modifier.fillMaxWidth()
            )
            CharacterProfilePropItem(
                title = "Status:",
                value = character.status,
                modifier = Modifier.fillMaxWidth()
            )
            CharacterProfilePropItem(
                title = "Gender:",
                value = character.gender,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CharacterProfilePropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCharacterProfileScreen() {
    RickAndMortyTheme {
        Surface {
            CharacterProfileScreen(
                character = Character(
                    id = 2565,
                    name = "Rick",
                    status = "Alive",
                    species = "Human",
                    gender = "Male",
                    image = ""
                ),
                onNavigateBack = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}