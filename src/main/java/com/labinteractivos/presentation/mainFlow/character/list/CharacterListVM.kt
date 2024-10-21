package com.laboratoriosinteractivos.lab10.presentation.mainFlow.character.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.laboratoriosinteractivos.lab10.data.model.Character
import com.laboratoriosinteractivos.lab10.data.local.CharacterDb
import com.laboratoriosinteractivos.lab10.data.local.dao.CharacterDao
import com.laboratoriosinteractivos.lab10.data.local.entity.mapToModel
import com.laboratoriosinteractivos.lab10.data.repository.LocalCharacterRepository
import com.laboratoriosinteractivos.lab10.di.Dependencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CharacterListState(
    val isLoading: Boolean = true,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)
class CharacterListViewModel(
    private val repository: LocalCharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState

    private var fetchJob: Job? = null

    init {
        viewModelScope.launch {
            repository.populateLocalCharacterDatabase()
        }
        getCharacterList()
    }

    private fun getCharacterList() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = CharacterListState(isLoading = true)

            try {
                delay(4000)

                repository.getAllCharacters().collect { characters ->
                    _uiState.value = CharacterListState(
                        isLoading = false,
                        data = characters
                    )
                }
            } catch (e: Exception) {
                _uiState.value = CharacterListState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun retryGetCharacters() {
        _uiState.value = CharacterListState(isLoading = true, hasError = false)
        getCharacterList()
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
                CharacterListViewModel(repository)
            }
        }
    }
}
