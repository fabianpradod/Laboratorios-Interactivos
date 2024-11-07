package com.lab12.presentation.mainFlow.location.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lab12.data.network.KtorRAMApi
import com.lab12.data.repository.LocalLocationRepository
import com.lab12.di.AppDependencies
import com.lab12.di.KtorDependencies
import com.lab12.domain.LocationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val locationRepository: LocationRepository
): ViewModel() {
    private var getDataJob: Job? = null
    private var _state = MutableStateFlow(LocationListState())
    val state = _state.asStateFlow()

    init {
        syncLocations()
    }

    fun onEvent(event: LocationListEvent) {
        when (event) {
            LocationListEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            LocationListEvent.RetryClick -> {
                syncLocations()
            }
        }
    }

    private fun syncLocations() {
        getDataJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }
            val success = locationRepository.initialSync()

            if (success) {
                val locations = locationRepository.getLocations()
                _state.update { it.copy(isLoading = false, locations = locations) }
            } else {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                val api = KtorRAMApi(KtorDependencies.provideHttpClient())
                LocationListViewModel(
                    locationRepository = LocalLocationRepository(
                        locationDao = appDatabase.locationDao(),
                        ramApi = api
                    )
                )
            }
        }
    }

}
