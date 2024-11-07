package com.lab12.presentation.mainFlow.location.list

sealed interface LocationListEvent {
    data object ForceError: LocationListEvent
    data object RetryClick: LocationListEvent
}
