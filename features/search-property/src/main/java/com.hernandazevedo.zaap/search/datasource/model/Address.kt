package com.hernandazevedo.zaap.search.datasource.model

data class Address(
    val city: String,
    val geoLocation: GeoLocation,
    val neighborhood: String
)