package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboratoriosinteractivos.lab10.data.model.Location
import com.laboratoriosinteractivos.lab10.data.source.LocationDb
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LocationListState(
    val isLoading: Boolean = true,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)

class LocationListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LocationListState())
    val uiState: StateFlow<LocationListState> = _uiState

    private var fetchJob: Job? = null

    private val locationDb = LocationDb()

    init {
        getLocationList()
    }

    private fun getLocationList() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = LocationListState(isLoading = true)

            try {
                delay(4000)

                val characters = locationDb.getAllLocations()

                _uiState.value = LocationListState(
                    isLoading = false,
                    data = characters
                )

            } catch (e: Exception) {
                _uiState.value = LocationListState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun retryGetLocations() {
        _uiState.value = LocationListState(isLoading = true, hasError = false)
        getLocationList()
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true, isLoading = false)
        fetchJob?.cancel()
    }
}