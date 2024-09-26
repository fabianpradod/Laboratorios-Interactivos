package com.labinteractivos.laboratorios.Lab8.locations

import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.labinteractivos.ui.theme.LabInteractivosTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.getLocationById
import com.labinteractivos.laboratorios.Lab8.Location
import com.labinteractivos.laboratorios.Lab8.getAllLocations

@Composable
fun LocationsRoute(
    onLocationsClick: (Location) -> Unit,
    onLCharactersClick: () -> Unit,
    onLLocationsClick: () -> Unit,
    onLProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LocationsScreen(
        onLocationsClick = onLocationsClick,
        onLCharactersClick = onLCharactersClick,
        onLLocationsClick = onLLocationsClick,
        onLProfileClick = onLProfileClick,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationsScreen(
    onLocationsClick: (Location) -> Unit,
    onLCharactersClick: () -> Unit,
    onLLocationsClick: () -> Unit,
    onLProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold (
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            val navController = rememberNavController()
            NavigationBar (
                modifier = Modifier.height(124.dp)
            ){
                NavigationBarItem(
                    selected = true,
                    onClick = { onLCharactersClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Characters"
                        )
                    },
                    label = {Text(text = "Characters")}
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { onLLocationsClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Locations"
                        )
                    },
                    label = {Text(text = "Locations")}
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { onLProfileClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    },
                    label = {Text(text = "Profile")}
                )
            }
        }
    ){ innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text("Locations")
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
                items(getAllLocations()) { location ->
                    val l_type = location.type
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationsClick(location) }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = location.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = l_type,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }

}
//
//@Preview
//@Composable
//private fun PreviewLocationsScreen() {
//    LabInteractivosTheme {
//        Surface {
//            LocationsScreen(
//                onLocationsClick = {},
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}