package com.labinteractivos.laboratorios.Lab8.homeLogIn

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

@Serializable
data object HomeLogInDestination

fun NavController.navigateToHomeLogInScreen(
    destination: HomeLogInDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.homeLogInScreen(
    onHomeLogInClick: () -> Unit
) {
    composable<HomeLogInDestination> {
        HomeLogInRoute(
            onHomeLogInClick = onHomeLogInClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}