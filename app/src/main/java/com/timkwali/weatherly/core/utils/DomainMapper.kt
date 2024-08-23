package com.timkwali.weatherly.core.utils

interface DomainMapper<DomainModel, Dto> {
    suspend fun mapToDomain(entity: DomainModel): Dto
}