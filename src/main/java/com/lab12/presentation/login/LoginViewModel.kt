package com.lab12.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lab12.data.repository.LocalCharacterRepository
import com.lab12.data.repository.LocalLocationRepository
import com.lab12.di.AppDependencies
import com.lab12.domain.CharacterRepository
import com.lab12.domain.LocationRepository
import com.lab12.domain.network.RAMApi
import com.lab12.data.network.KtorRAMApi
import com.lab12.di.KtorDependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.ktor.client.HttpClient

class LoginViewModel(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onLogin() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (characterRepository.initialSync() && locationRepository.initialSync()) {
                _state.update { it.copy(
                    isLoading = false,
                    loginSuccessful = true
                )}
            } else {
                _state.update { it.copy(
                    isLoading = false,
                    loginSuccessful = false
                )}
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)

                val api = KtorRAMApi(KtorDependencies.provideHttpClient())


                LoginViewModel(
                    characterRepository = LocalCharacterRepository(
                        appDatabase.characterDao(),
                        ramApi = api
                    ),
                    locationRepository = LocalLocationRepository(
                        appDatabase.locationDao(),
                        ramApi = api
                    )
                )
            }
        }
    }
}
