package com.laboratoriosinteractivos.lab10.presentation.mainFlow.character.profile


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.laboratoriosinteractivos.lab10.data.model.Character
import com.laboratoriosinteractivos.lab10.data.local.CharacterDb
import com.laboratoriosinteractivos.lab10.data.repository.LocalCharacterRepository
import com.laboratoriosinteractivos.lab10.di.Dependencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CharacterProfileState(
    val isLoading: Boolean = true,
    val data: Character? = null,
    val hasError: Boolean = false
)

class CharacterProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: LocalCharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterProfileState())
    val uiState: StateFlow<CharacterProfileState> = _uiState

    private var fetchJob: Job? = null

    private val profile = savedStateHandle.toRoute<CharacterProfileDestination>()

    init {
        getCharacterProfile()
    }

    fun getCharacterProfile() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = CharacterProfileState(isLoading = true)

            try {
                delay(2000)

                val character = repository.getCharacterById(profile.characterId)

                _uiState.value = CharacterProfileState(
                    isLoading = false,
                    data = character
                )

            } catch (e: Exception) {
                _uiState.value = CharacterProfileState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun retryGetCharacter() {
        _uiState.value = CharacterProfileState(isLoading = true, hasError = false)
        getCharacterProfile()
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
                val repository = LocalCharacterRepository(db.CharacterDao())
                val savedStateHandle = createSavedStateHandle()
                CharacterProfileViewModel(savedStateHandle, repository)
            }
        }
    }
}