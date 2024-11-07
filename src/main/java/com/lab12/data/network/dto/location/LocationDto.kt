package com.lab12.data.network.dto.location

import com.lab12.data.local.entity.LocationEntity
import kotlinx.serialization.Serializable


@Serializable
data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationDto.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}