package com.lab12.domain

import com.lab12.data.model.Location


interface LocationRepository {
    suspend fun initialSync(): Boolean
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}