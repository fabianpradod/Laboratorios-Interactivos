package com.lab12.data.network

import com.lab12.data.network.dto.character.CharacterListDto
import com.lab12.data.network.dto.location.LocationListDto
import com.lab12.data.network.util.safeCall
import com.lab12.domain.network.util.NetworkError
import com.lab12.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import com.lab12.domain.network.RAMApi

class KtorRAMApi(
    private val httpClient: HttpClient
) : RAMApi {
    override suspend fun getAllCharacters(): Result<CharacterListDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/character?limit=20")
        }
    }

    override suspend fun getAllLocations(): Result<LocationListDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/location?limit=20")
        }

    }
}
