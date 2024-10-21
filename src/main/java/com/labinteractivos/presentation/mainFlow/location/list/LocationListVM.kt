package com.laboratoriosinteractivos.lab10.presentation.mainFlow.location.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.laboratoriosinteractivos.lab10.data.model.Location
import com.laboratoriosinteractivos.lab10.data.local.LocationDb
import com.laboratoriosinteractivos.lab10.data.repository.LocalLocationRepository
import com.laboratoriosinteractivos.lab10.di.Dependencies
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

class LocationListViewModel(
    private val repository: LocalLocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationListState())
    val uiState: StateFlow<LocationListState> = _uiState

    private var fetchJob: Job? = null


    init {
        viewModelScope.launch {
            repository.populateLocalLocationDatabase()
        }
        getLocationList()
    }

    private fun getLocationList() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = LocationListState(isLoading = true)

            try {
                delay(4000)


                repository.getAllLocations().collect { locations ->
                    _uiState.value = LocationListState(
                        isLoading = false,
                        data = locations
                    )
                }
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
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                val repository = LocalLocationRepository(db.LocationDao())
                LocationListViewModel(repository)
            }
        }
    }
}