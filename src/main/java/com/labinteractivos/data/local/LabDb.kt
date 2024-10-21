package com.laboratoriosinteractivos.lab10.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laboratoriosinteractivos.lab10.data.local.dao.CharacterDao
import com.laboratoriosinteractivos.lab10.data.local.dao.LocationDao
import com.laboratoriosinteractivos.lab10.data.local.entity.CharacterEntity
import com.laboratoriosinteractivos.lab10.data.local.entity.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        LocationEntity::class
    ],
    version = 1
)
abstract class LabDb: RoomDatabase() {
    abstract fun CharacterDao(): CharacterDao
    abstract fun LocationDao(): LocationDao
}