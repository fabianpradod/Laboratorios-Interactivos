package com.labinteractivos.laboratorios.Lab8.charDetatils

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharDetailsDestination(
    val id: Int
)

fun NavController.navigateToCharDetailsScreen(
    destination: CharDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.charDetailsScreen(
    onGoBackClick: () -> Unit
) {
    composable<CharDetailsDestination> { backStackEntry ->
        val destination: CharDetailsDestination = backStackEntry.toRoute()
        CharDetailsRoute(
            id = destination.id,
            onGoBackClick = onGoBackClick
        )
    }
}
