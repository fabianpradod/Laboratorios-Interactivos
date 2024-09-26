package com.labinteractivos.laboratorios.Lab8.characters

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.labinteractivos.laboratorios.Lab8.Character
import com.labinteractivos.laboratorios.Lab8.NavControl.BNDestination

@Serializable
data object CharactersDestination: BNDestination

fun NavController.navigateToCharactersScreen(
    destination: CharactersDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.charactersScreen(
    onCharactersClick: (Character) -> Unit,
    onCCharactersClick: () -> Unit,
    onCLocationsClick: () -> Unit,
    onCProfileClick: () -> Unit,
) {
    composable<CharactersDestination> {
        CharactersRoute(
            onCharactersClick = onCharactersClick,
            onCCharactersClick = onCCharactersClick,
            onCLocationsClick = onCLocationsClick,
            onCProfileClick = onCProfileClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}