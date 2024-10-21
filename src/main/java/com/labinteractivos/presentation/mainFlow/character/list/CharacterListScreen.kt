package com.laboratoriosinteractivos.lab10.presentation.mainFlow.character.list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laboratoriosinteractivos.lab10.data.model.Character
import com.laboratoriosinteractivos.lab10.data.local.CharacterDb
import com.laboratoriosinteractivos.lab10.ui.theme.RickAndMortyTheme



@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel(factory = CharacterListViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingLayout(onLoadingClick = { viewModel.triggerError() })
        }

        uiState.hasError -> {
            ErrorLayout(onRetryClick = { viewModel.retryGetCharacters() })
        }

        uiState.data != null -> {
            val characters = uiState.data ?: emptyList()
            CharacterListScreen(
                characters = characters,
                onCharacterClick = onCharacterClick,
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
            Text(text = "Loading Characters", style = MaterialTheme.typography.bodyMedium)
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
            Text(text = "Error al obtener el listado de personajes", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
private fun CharacterListScreen(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(characters) { item ->
            CharacterItem(
                character = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClick(item.id) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(character.id % (imageBackgroundColors.count() - 1))],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    Icons.Outlined.Person, contentDescription = "Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = character.name)
            Text(
                text = "${character.species} * ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


//
//@Preview
//@Composable
//private fun PreviewCharacterListScreen() {
//    RickAndMortyTheme() {
//        Surface {
//            val db = CharacterDb()
//            CharacterListScreen(
//                characters = db.getAllCharacters().take(6),
//                onCharacterClick = {},
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}