package com.lab12.presentation.mainFlow.character.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lab12.ui.theme.Lab12Theme
import com.lab12.data.model.Character
import com.lab12.data.source.CharacterDb
import com.lab12.presentation.common.LoadingView

@Composable
fun CharacterProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: CharacterProfileViewModel = viewModel(factory = CharacterProfileViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterProfileScreen(
        state = state,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterProfileScreen(
    state: CharacterProfileState,
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
        CharacterProfileContent(
            character = state.data,
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CharacterProfileContent(
    character: Character?,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (isLoading) {
            LoadingView(
                loadingText = "Obteniendo información",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            character?.let {
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
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer,
                                shape = CircleShape
                            )
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

private class CharacterProfileParameterProvider : CollectionPreviewParameterProvider<CharacterProfileState>(
    listOf(
        CharacterProfileState(),
        CharacterProfileState(
            isLoading = false,
            data = Character(
                id = 2565,
                name = "Rick",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = ""
            )
        )
    )
)

@Preview
@Composable
private fun PreviewCharacterProfileScreen(
    @PreviewParameter(CharacterProfileParameterProvider::class) state: CharacterProfileState
) {
    Lab12Theme {
        Surface {
            CharacterProfileScreen(
                state = state,
                onNavigateBack = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
