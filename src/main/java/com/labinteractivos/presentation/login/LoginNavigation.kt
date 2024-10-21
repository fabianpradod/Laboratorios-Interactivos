package com.laboratoriosinteractivos.lab10.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.laboratoriosinteractivos.lab10.data.repository.LocalProfileRepository
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit,
    localProfileRepository: LocalProfileRepository
) {
    composable<LoginDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
            localProfileRepository = localProfileRepository
        )
    }
}