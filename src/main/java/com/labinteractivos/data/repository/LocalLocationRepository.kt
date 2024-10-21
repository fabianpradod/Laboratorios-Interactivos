package com.laboratoriosinteractivos.lab10.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.laboratoriosinteractivos.lab10.data.local.LocationDb
import com.laboratoriosinteractivos.lab10.data.local.dao.LocationDao
import com.laboratoriosinteractivos.lab10.data.local.entity.mapToEntity
import com.laboratoriosinteractivos.lab10.data.local.entity.mapToModel
import com.laboratoriosinteractivos.lab10.data.model.Location

class LocalLocationRepository(
    private val locationDao: LocationDao
) {
    fun getAllLocations(): Flow<List<Location>> =
        locationDao.getAllLocations().map { localLocations ->
            localLocations.map { localLocation ->
                localLocation.mapToModel()
            }
        }

    suspend fun getLocationById(id: Int): Location? {
        val locationEntities = locationDao.getLocationById(id)
        return locationEntities.firstOrNull()?.mapToModel()
    }

    suspend fun populateLocalLocationDatabase() {
        val remoteLocations = LocationDb.getAllLocations()
        val localLocationEntities = remoteLocations.map { remoteLocation ->
            remoteLocation.mapToEntity()
        }

        locationDao.insertAll(localLocationEntities)
    }
}