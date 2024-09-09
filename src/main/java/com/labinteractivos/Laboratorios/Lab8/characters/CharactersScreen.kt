package com.labinteractivos.laboratorios.Lab8.characters

import androidx.compose.foundation.background
import androidx.compose.material3.Surface
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.labinteractivos.ui.theme.LabInteractivosTheme
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.getAllCharacters



@Composable
fun CharactersRoute(
    onCharactersClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    CharactersScreen(
        onCharactersClick = onCharactersClick,
        modifier = modifier
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharactersScreen(
    onCharactersClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text("Characters")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(getAllCharacters()) { character ->
                val c_species = character.species
                val c_status = character.status
                val c_image = character.image
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharactersClick(character) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                    )
//                    AsyncImage(
//                        model = c_image,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(120.dp)
//                            .clip(CircleShape)
//                            .background(Color.Gray)
//                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "$c_species - $c_status",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCharactersScreen() {
    LabInteractivosTheme {
        Surface {
            CharactersScreen(
                onCharactersClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}