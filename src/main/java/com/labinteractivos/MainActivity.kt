package com.labinteractivos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabInteractivosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                            }
                        )
                        charDetailsScreen (
                            onGoBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}