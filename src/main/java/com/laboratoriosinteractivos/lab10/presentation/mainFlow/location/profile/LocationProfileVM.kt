package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.laboratoriosinteractivos.lab10.data.model.Location
import com.laboratoriosinteractivos.lab10.data.source.LocationDb
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LocationProfileState(
    val isLoading: Boolean = true,
    val data: Location? = null,
    val hasError: Boolean = false
)

class LocationProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationProfileState())
    val uiState: StateFlow<LocationProfileState> = _uiState

    private var fetchJob: Job? = null

    val locationDb = LocationDb()
    private val profile = savedStateHandle.toRoute<LocationProfileDestination>()

    init {
        getLocationProfile()
    }

    fun getLocationProfile() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = LocationProfileState(isLoading = true)

            try {
                delay(2000)

                val location = locationDb.getLocationById(profile.locationId)

                _uiState.value = LocationProfileState(
                    isLoading = false,
                    data = location
                )

            } catch (e: Exception) {
                _uiState.value = LocationProfileState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun retryGetLocation() {
        _uiState.value = LocationProfileState(isLoading = true, hasError = false)
        getLocationProfile()
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true, isLoading = false)
        fetchJob?.cancel()
    }
}