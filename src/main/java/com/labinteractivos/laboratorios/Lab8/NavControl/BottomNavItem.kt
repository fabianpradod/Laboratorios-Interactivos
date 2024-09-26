package com.labinteractivos.laboratorios.Lab8.NavControl

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val destination: BNDestination
)