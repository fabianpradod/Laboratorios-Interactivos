package com.lab12.data.repository

import com.lab12.data.local.dao.CharacterDao
import com.lab12.data.local.entity.mapToEntity
import com.lab12.data.local.entity.mapToModel
import com.lab12.data.model.Character
import com.lab12.data.network.dto.character.toEntity
import com.lab12.domain.CharacterRepository
import com.lab12.domain.network.RAMApi
import com.lab12.data.network.KtorRAMApi
import com.lab12.domain.network.util.onError
import com.lab12.domain.network.util.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class LocalCharacterRepository(
    private val characterDao: CharacterDao,
    private val ramApi: RAMApi
) : CharacterRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            if (characterDao.getAllCharacters().isEmpty()) {
                ramApi.getAllCharacters()
                    .onSuccess { characterListDto ->
                        val charactersToInsert = characterListDto.results.map { it.toEntity() }
                        characterDao.insertAll(charactersToInsert)
                    }
                    .onError { error ->
                        println("Error fetching characters: $error")
                        return false
                    }
            }
            true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println("Unexpected error during initialSync: ${e.message}")
            false
        }
    }

    override suspend fun getCharacters(): List<Character> {
        delay(4000)
        return characterDao.getAllCharacters().map { it.mapToModel() }
    }

    override suspend fun getCharacterById(id: Int): Character {
        delay(2000)
        return characterDao.getCharacterById(id).mapToModel()
    }
}
