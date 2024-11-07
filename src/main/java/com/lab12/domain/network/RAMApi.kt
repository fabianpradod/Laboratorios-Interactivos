package com.lab12.domain.network

import com.lab12.data.network.dto.character.CharacterListDto
import com.lab12.data.network.dto.location.LocationListDto
import com.lab12.domain.network.util.NetworkError
import com.lab12.domain.network.util.Result

interface RAMApi {
    suspend fun getAllCharacters(): Result<CharacterListDto, NetworkError>
    suspend fun getAllLocations(): Result<LocationListDto, NetworkError>
}