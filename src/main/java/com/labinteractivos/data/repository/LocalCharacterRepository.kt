package com.laboratoriosinteractivos.lab10.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.laboratoriosinteractivos.lab10.data.local.CharacterDb
import com.laboratoriosinteractivos.lab10.data.local.dao.CharacterDao
import com.laboratoriosinteractivos.lab10.data.local.entity.mapToEntity
import com.laboratoriosinteractivos.lab10.data.local.entity.mapToModel
import com.laboratoriosinteractivos.lab10.data.model.Character

class LocalCharacterRepository(
    private val characterDao: CharacterDao
) {
    fun getAllCharacters(): Flow<List<Character>> =
        characterDao.getAllCharacters().map { localCharacters ->
            localCharacters.map { localCharacter ->
                localCharacter.mapToModel()
            }
        }

    suspend fun getCharacterById(id: Int): Character? {
        val characterEntities = characterDao.getCharacterById(id)
        return characterEntities.firstOrNull()?.mapToModel()
    }

    suspend fun populateLocalCharacterDatabase() {
        val remoteCharacters = CharacterDb.getAllCharacters()
        val localCharacterEntities = remoteCharacters.map { remoteCharacter ->
            remoteCharacter.mapToEntity()
        }

        characterDao.insertAll(localCharacterEntities)
    }
}
