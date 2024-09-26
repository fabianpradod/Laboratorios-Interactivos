package com.labinteractivos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.labinteractivos.laboratorios.Lab8.locDetails.LocDetailsDestination
import com.labinteractivos.laboratorios.Lab8.locDetails.locDetailsScreen
import com.labinteractivos.laboratorios.Lab8.locDetails.navigateToLocDetailsScreen
import com.labinteractivos.laboratorios.Lab8.locations.locationsScreen
import com.labinteractivos.laboratorios.Lab8.charDetatils.CharDetailsDestination
import com.labinteractivos.laboratorios.Lab8.charDetatils.navigateToCharDetailsScreen
import com.labinteractivos.laboratorios.Lab8.characters.CharactersDestination
import com.labinteractivos.laboratorios.Lab8.characters.charactersScreen
import com.labinteractivos.laboratorios.Lab8.characters.navigateToCharactersScreen
import com.labinteractivos.laboratorios.Lab8.homeLogIn.HomeLogInDestination
import com.labinteractivos.laboratorios.Lab8.homeLogIn.homeLogInScreen
import com.labinteractivos.ui.theme.LabInteractivosTheme
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.charDetatils.charDetailsScreen
import com.labinteractivos.laboratorios.Lab8.homeLogIn.navigateToHomeLogInScreen
import com.labinteractivos.laboratorios.Lab8.locations.LocationsDestination
import com.labinteractivos.laboratorios.Lab8.locations.navigateToLocationsScreen
import com.labinteractivos.laboratorios.Lab8.profile.ProfileDestination
import com.labinteractivos.laboratorios.Lab8.profile.navigateToProfileScreen
import com.labinteractivos.laboratorios.Lab8.profile.profileScreen
import com.labinteractivos.laboratorios.Lab8.NavControl.BottomNavItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabInteractivosTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = HomeLogInDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        homeLogInScreen(
                            onHomeLogInClick = {
                                navController.navigateToCharactersScreen(
                                    destination = CharactersDestination,
                                    navOptions =  navOptions {
                                        popUpTo<HomeLogInDestination>{
                                            inclusive = true
                                            saveState = false
                                        }
                                        restoreState = false
                                    }
                                )
                            }
                        )
                        charactersScreen (
                            onCharactersClick = { character ->
                                navController.navigateToCharDetailsScreen(
                                    destination = CharDetailsDestination(
                                        id = character.id
                                    )
                                )
                            },
                            onCCharactersClick = {
                                navController.navigateToCharactersScreen(
                                    destination = CharactersDestination
                                )
                            },
                            onCLocationsClick = {
                                navController.navigateToLocationsScreen(
                                    destination = LocationsDestination
                                )
                            },onCProfileClick = {
                                navController.navigateToProfileScreen(
                                    destination = ProfileDestination
                                )
                            }
                        )
                        charDetailsScreen (
                            onGoBackClick = {
                                navController.navigateUp()
                            }
                        )
                        locationsScreen (
                            onLocationsClick = { location ->
                                navController.navigateToLocDetailsScreen(
                                    destination = LocDetailsDestination(
                                        id = location.id
                                    )
                                )
                            },
                            onLCharactersClick = {
                                navController.navigateToCharactersScreen(
                                    destination = CharactersDestination
                                )
                            },
                            onLLocationsClick = {
                                navController.navigateToLocationsScreen(
                                    destination = LocationsDestination
                                )
                            },onLProfileClick = {
                                navController.navigateToProfileScreen(
                                    destination = ProfileDestination
                                )
                            }
                        )
                        locDetailsScreen (
                            onGoBackClick = {
                                navController.navigateUp()
                            }
                        )
                        profileScreen (
                            onLogOutClick = {
                                navController.navigateToHomeLogInScreen(
                                    destination = HomeLogInDestination,
                                    navOptions =  navOptions {
                                        popUpTo<ProfileDestination>{
                                            inclusive = true
                                            saveState = false
                                        }
                                        restoreState = true
                                    }
                                )
                            },
                            onPCharactersClick = {
                                navController.navigateToCharactersScreen(
                                    destination = CharactersDestination
                                )
                            },
                            onPLocationsClick = {
                                navController.navigateToLocationsScreen(
                                    destination = LocationsDestination
                                )
                            },onPProfileClick = {
                                navController.navigateToProfileScreen(
                                    destination = ProfileDestination
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}