package com.lab12.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lab12.presentation.login.LoginDestination
import com.lab12.presentation.login.loginScreen
import com.lab12.presentation.mainFlow.mainNavigationGraph
import com.lab12.presentation.mainFlow.navigateToMainGraph


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToMainGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )
        mainNavigationGraph(
            onLogOutClick = {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }
        )
    }
}