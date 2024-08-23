package com.timkwali.weatherly.weather.domain.model.searchlocation

import com.timkwali.weatherly.core.utils.DomainMapper
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponseItem

class LocationMapper: DomainMapper<LocationSearchResponseItem, LocationState> {
    override suspend fun mapToDomain(entity: LocationSearchResponseItem): LocationState {
        return LocationState(
            displayName = "${entity.name}- ${entity.state}(${entity.country})",
            latitude = "${entity.lat ?: ""}",
            longitude = "${entity.lon ?: ""}"
        )
    }
}