package com.laboratoriosinteractivos.lab10.presentation.mainFlow.character.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboratoriosinteractivos.lab10.data.model.Character
import com.laboratoriosinteractivos.lab10.data.source.CharacterDb
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

class CharacterListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState

    private var fetchJob: Job? = null

    private val characterDb = CharacterDb()

    init {
        getCharacterList()
    }

    private fun getCharacterList() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _uiState.value = CharacterListState(isLoading = true)

            try {
                delay(4000)

                val characters = characterDb.getAllCharacters()

                _uiState.value = CharacterListState(
                    isLoading = false,
                    data = characters
                )

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
}