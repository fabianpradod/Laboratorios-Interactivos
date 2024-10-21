package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.laboratoriosinteractivos.lab10.data.model.Location
import com.laboratoriosinteractivos.lab10.data.local.LocationDb
import com.laboratoriosinteractivos.lab10.data.repository.LocalLocationRepository
import com.laboratoriosinteractivos.lab10.di.Dependencies
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
    savedStateHandle: SavedStateHandle,
    private val repository: LocalLocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationProfileState())
    val uiState: StateFlow<LocationProfileState> = _uiState

    private var fetchJob: Job? = null

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

                val location = repository.getLocationById(profile.locationId)

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
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                val repository = LocalLocationRepository(db.LocationDao())
                val savedStateHandle = createSavedStateHandle()
                LocationProfileViewModel(savedStateHandle, repository)
            }
        }
    }
}