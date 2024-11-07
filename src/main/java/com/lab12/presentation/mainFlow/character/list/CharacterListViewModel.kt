package com.lab12.presentation.mainFlow.character.list



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lab12.data.network.KtorRAMApi
import com.lab12.data.repository.LocalCharacterRepository
import com.lab12.di.AppDependencies
import com.lab12.di.KtorDependencies
import com.lab12.domain.CharacterRepository
import com.lab12.domain.network.RAMApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    init {
        syncCharacters()
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            CharacterListEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            CharacterListEvent.RetryClick -> {
                syncCharacters()
            }
        }
    }

    private fun syncCharacters() {
        getDataJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }
            val success = characterRepository.initialSync()

            if (success) {
                val characters = characterRepository.getCharacters()
                _state.update { it.copy(isLoading = false, characters = characters) }
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
                CharacterListViewModel(
                    characterRepository = LocalCharacterRepository(
                        characterDao = appDatabase.characterDao(),
                        ramApi = api
                    )
                )
            }
        }
    }
}
