package com.lab12.data.network.dto.location


import kotlinx.serialization.Serializable

@Serializable
data class LocationListDto(
    val results: List<LocationDto>
)
