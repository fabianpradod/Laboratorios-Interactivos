package com.labinteractivos.laboratorios.Lab8.locDetails


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.labinteractivos.ui.theme.LabInteractivosTheme
import com.labinteractivos.R
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.getAllCharacters
import com.labinteractivos.laboratorios.Lab8.getCharacterById
import com.labinteractivos.laboratorios.Lab8.getLocationById


@Composable
fun LocDetailsRoute(
    id: Int,
    onGoBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val location = getLocationById(id)
    LocDetailsScreen(
        id = id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
        onGoBackClick = onGoBackClick,
        modifier = Modifier.fillMaxSize()
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocDetailsScreen(
    id: Int,
    name: String,
    type: String,
    dimension: String,
    onGoBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text("Location Detail")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(onClick = onGoBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Id:", fontWeight = FontWeight.Medium)
                Text("Type:", fontWeight = FontWeight.Medium)
                Text("Dimensions:", fontWeight = FontWeight.Medium)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$name",
                    )
                Text(
                    text = "$type",
                    )
                Text(
                    text = "$dimension",
                    )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLocDetailsScreen() {
    LabInteractivosTheme {
        Surface {
            LocDetailsScreen(
                id = 1,
                name = "Fabian",
                type = "Space Station",
                dimension = "3D",
                onGoBackClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}