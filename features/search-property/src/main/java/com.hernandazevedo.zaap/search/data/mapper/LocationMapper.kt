package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.Location
import com.hernandazevedo.zaap.search.domain.model.LocationDomain

object LocationMapper: BaseMapper<Location, LocationDomain>() {
    override fun mapFrom(source: LocationDomain): Location {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: Location): LocationDomain =
        LocationDomain(
            lat = source.lat,
            lon = source.lon
        )
}