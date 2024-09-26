package com.labinteractivos.laboratorios.Lab8.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.labinteractivos.laboratorios.Lab8.NavControl.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination: BNDestination


fun NavController.navigateToProfileScreen(
    destination: ProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit,
    onPCharactersClick: () -> Unit,
    onPLocationsClick: () -> Unit,
    onPProfileClick: () -> Unit,
) {
    composable<ProfileDestination> { backStackEntry ->
        val destination: ProfileDestination = backStackEntry.toRoute()
        ProfileRoute(
            onLogOutClick = onLogOutClick,
            onPCharactersClick = onPCharactersClick,
            onPLocationsClick = onPLocationsClick,
            onPProfileClick = onPProfileClick,
        )
    }
}
