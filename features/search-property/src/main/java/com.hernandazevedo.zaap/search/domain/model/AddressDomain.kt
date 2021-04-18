package com.hernandazevedo.zaap.search.domain.model

data class AddressDomain(
    val city: String,
    val geoLocation: GeoLocationDomain,
    val neighborhood: String
)