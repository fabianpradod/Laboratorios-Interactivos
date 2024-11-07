package com.lab12.domain
import com.lab12.data.model.Character


interface CharacterRepository {
    suspend fun initialSync(): Boolean
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}