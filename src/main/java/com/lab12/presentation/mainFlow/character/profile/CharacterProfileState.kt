package com.lab12.presentation.mainFlow.character.profile

import com.lab12.data.model.Character

data class CharacterProfileState(
    val data: Character? = null,
    val isLoading: Boolean = true
)
