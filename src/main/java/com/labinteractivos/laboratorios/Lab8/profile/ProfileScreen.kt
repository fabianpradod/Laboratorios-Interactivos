package com.labinteractivos.laboratorios.Lab8.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.getAllCharacters
import com.labinteractivos.laboratorios.Lab8.getCharacterById



@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit,
    onPCharactersClick: () -> Unit,
    onPLocationsClick: () -> Unit,
    onPProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfileScreen(
        onLogOutClick = onLogOutClick,
        onPCharactersClick = onPCharactersClick,
        onPLocationsClick = onPLocationsClick,
        onPProfileClick = onPProfileClick,
        modifier = Modifier.fillMaxSize()
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    onLogOutClick: () -> Unit,
    onPCharactersClick: () -> Unit,
    onPLocationsClick: () -> Unit,
    onPProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            val navController = rememberNavController()
            NavigationBar(
                modifier = Modifier.height(124.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { onPCharactersClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Locations"
                        )
                    },
                    label = {Text(text = "Locations")}
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { onPLocationsClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Profile"
                        )
                    },
                    label = {Text(text = "Profile")}
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { onPProfileClick() },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {
                    Text("Profile")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.fabian_foto),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Nombre:", fontWeight = FontWeight.Medium)
                    Text("Carne:", fontWeight = FontWeight.Medium)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("Fabian Prado Dluzniewski")
                    Text("23427")
                }
            }
            Button(
                onClick = onLogOutClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 40.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )  {
                Text("Cerrar Sesion", color = MaterialTheme.colorScheme.onSecondary)
            }
        }
    }
}
//
//@Preview
//@Composable
//private fun PreviewProfileScreen() {
//    LabInteractivosTheme {
//        Surface {
//            ProfileScreen(
//                onLogOutClick = {},
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}