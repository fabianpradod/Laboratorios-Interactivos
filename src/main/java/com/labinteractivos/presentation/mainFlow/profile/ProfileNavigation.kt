package com.laboratoriosinteractivos.lab10.presentation.mainFlow.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.laboratoriosinteractivos.lab10.data.repository.LocalProfileRepository
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit,
    localProfileRepository: LocalProfileRepository
) {
    composable<ProfileDestination> {
        ProfileRoute(
            onLogOutClick = onLogOutClick,
            localProfileRepository = localProfileRepository
        )
    }
}