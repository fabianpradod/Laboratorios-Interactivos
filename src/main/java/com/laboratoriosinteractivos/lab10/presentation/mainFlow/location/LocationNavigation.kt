package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.list.LocationListDestination
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.list.locationListScreen
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.profile.locationProfileScreen
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.profile.navigateToLocationProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocationsNavGraph

fun NavController.navigateToLocationsGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationsNavGraph, navOptions)
}

fun NavGraphBuilder.locationsGraph(
    navController: NavController
) {
    navigation<LocationsNavGraph>(
        startDestination = LocationListDestination
    ) {
        locationListScreen(
            onLocationClick = navController::navigateToLocationProfileScreen
        )
        locationProfileScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}