package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.GeoLocation
import com.hernandazevedo.zaap.search.domain.model.GeoLocationDomain

object GeoLocationMapper: BaseMapper<GeoLocation, GeoLocationDomain>() {
    override fun mapFrom(source: GeoLocationDomain): GeoLocation {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: GeoLocation): GeoLocationDomain =
        GeoLocationDomain(
            location = LocationMapper.mapTo(source.location),
            precision = source.precision
        )
}