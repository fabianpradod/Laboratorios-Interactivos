package com.laboratoriosinteractivos.lab10.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.laboratoriosinteractivos.lab10.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters() : Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacterById(id: Int): List<CharacterEntity>
}
