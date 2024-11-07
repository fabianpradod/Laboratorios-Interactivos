package com.lab12.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lab12.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity
}