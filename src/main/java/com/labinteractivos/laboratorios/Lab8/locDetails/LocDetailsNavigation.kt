package com.labinteractivos.laboratorios.Lab8.locDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocDetailsDestination(
    val id: Int
)

fun NavController.navigateToLocDetailsScreen(
    destination: LocDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locDetailsScreen(
    onGoBackClick: () -> Unit
) {
    composable<LocDetailsDestination> { backStackEntry ->
        val destination: LocDetailsDestination = backStackEntry.toRoute()
        LocDetailsRoute(
            id = destination.id,
            onGoBackClick = onGoBackClick
        )
    }
}
