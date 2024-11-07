package com.lab12.presentation.mainFlow.location.list

import com.lab12.data.model.Location


data class LocationListState(
    val isLoading: Boolean = true,
    val locations: List<Location> = emptyList(),
    val isError: Boolean = false
)
