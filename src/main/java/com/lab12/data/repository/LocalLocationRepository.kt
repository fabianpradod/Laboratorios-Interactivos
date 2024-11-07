package com.lab12.data.repository


import com.lab12.data.local.dao.LocationDao
import com.lab12.data.local.entity.mapToEntity
import com.lab12.data.local.entity.mapToModel
import com.lab12.data.model.Location
import com.lab12.data.network.dto.location.toEntity
import com.lab12.data.source.LocationDb
import com.lab12.domain.LocationRepository
import com.lab12.domain.network.RAMApi
import com.lab12.domain.network.util.onError
import com.lab12.domain.network.util.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class LocalLocationRepository(
    private val locationDao: LocationDao,
    private val ramApi: RAMApi
): LocationRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            if (locationDao.getAllLocations().isEmpty()) {
                ramApi.getAllLocations()
                    .onSuccess { locationListDto ->
                        val locationsToInsert = locationListDto.results.map { it.toEntity() }
                        locationDao.insertAll(locationsToInsert)
                    }
                    .onError { error ->
                        println("Error fetching locations: $error")
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

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDao.getAllLocations().map { it.mapToModel() }
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDao.getLocationById(id).mapToModel()
    }
}