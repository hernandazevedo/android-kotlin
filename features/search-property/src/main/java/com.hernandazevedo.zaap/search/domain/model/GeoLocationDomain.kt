package com.hernandazevedo.zaap.search.domain.model

data class GeoLocationDomain(
    val location: LocationDomain,
    val precision: String
)