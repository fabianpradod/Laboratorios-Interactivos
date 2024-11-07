package com.lab12.data.network.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDto(
    val results: List<CharacterDto>
)
