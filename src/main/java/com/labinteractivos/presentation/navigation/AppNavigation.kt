package com.laboratoriosinteractivos.lab10.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.laboratoriosinteractivos.lab10.presentation.login.LoginDestination
import com.laboratoriosinteractivos.lab10.presentation.login.loginScreen
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.mainNavigationGraph
import com.laboratoriosinteractivos.lab10.presentation.mainFlow.navigateToMainGraph
import androidx.compose.ui.platform.LocalContext
import com.laboratoriosinteractivos.lab10.data.repository.LocalProfileRepository


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current
    val localProfileRepository = LocalProfileRepository(context)

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
            },
            localProfileRepository = localProfileRepository
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