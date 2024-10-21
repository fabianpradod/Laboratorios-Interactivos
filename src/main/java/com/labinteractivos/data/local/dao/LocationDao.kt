package com.laboratoriosinteractivos.lab10.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.laboratoriosinteractivos.lab10.data.local.entity.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM LocationEntity")
    fun getAllLocations() : Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query("SELECT * FROM LocationEntity WHERE id = :id")
    suspend fun getLocationById(id: Int): List<LocationEntity>
}