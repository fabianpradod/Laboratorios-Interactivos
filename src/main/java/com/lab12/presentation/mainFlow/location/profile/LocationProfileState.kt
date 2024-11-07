package com.lab12.presentation.mainFlow.location.profile

import com.lab12.data.model.Location

data class LocationProfileState(
    val data: Location? = null,
    val isLoading: Boolean = true
)
