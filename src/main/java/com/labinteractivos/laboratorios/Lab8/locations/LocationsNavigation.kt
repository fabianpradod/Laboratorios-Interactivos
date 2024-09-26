package com.labinteractivos.laboratorios.Lab8.locations

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.labinteractivos.laboratorios.Lab8.Location
import com.labinteractivos.laboratorios.Lab8.NavControl.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data object LocationsDestination: BNDestination

fun NavController.navigateToLocationsScreen(
    destination: LocationsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationsScreen(
    onLocationsClick: (Location) -> Unit,
    onLCharactersClick: () -> Unit,
    onLLocationsClick: () -> Unit,
    onLProfileClick: () -> Unit,
) {
    composable<LocationsDestination> {
        LocationsRoute(
            onLocationsClick = onLocationsClick,
            onLCharactersClick = onLCharactersClick,
            onLLocationsClick = onLLocationsClick,
            onLProfileClick = onLProfileClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}