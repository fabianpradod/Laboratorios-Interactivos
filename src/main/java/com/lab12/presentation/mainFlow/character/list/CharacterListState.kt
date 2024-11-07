package com.lab12.presentation.mainFlow.character.list

import com.lab12.data.model.Character

data class CharacterListState(
    val isLoading: Boolean = true,
    val characters: List<Character> = emptyList(),
    val isError: Boolean = false
)
