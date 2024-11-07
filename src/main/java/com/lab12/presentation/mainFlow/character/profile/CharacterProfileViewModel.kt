package com.lab12.presentation.mainFlow.character.profile



import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.lab12.data.network.KtorRAMApi
import com.lab12.data.repository.LocalCharacterRepository
import com.lab12.di.AppDependencies
import com.lab12.di.KtorDependencies
import com.lab12.domain.CharacterRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val characterProfile = savedStateHandle.toRoute<CharacterProfileDestination>()
    private val _state: MutableStateFlow<CharacterProfileState> = MutableStateFlow(CharacterProfileState())
    val state = _state.asStateFlow()

    init {
        getCharacterData()
    }

    private fun getCharacterData() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(isLoading = true)
            }

            val location = characterRepository.getCharacterById(characterProfile.characterId)

            _state.update { state ->
                state.copy(
                    data = location,
                    isLoading = false
                )
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)


                val api = KtorRAMApi(KtorDependencies.provideHttpClient())

                CharacterProfileViewModel(
                    characterRepository = LocalCharacterRepository(
                        characterDao = appDatabase.characterDao(),
                        ramApi = api
                    ),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
