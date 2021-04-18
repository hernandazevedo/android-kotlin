package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.Address
import com.hernandazevedo.zaap.search.domain.model.AddressDomain

object AddressMapper: BaseMapper<Address, AddressDomain>() {
    override fun mapFrom(source: AddressDomain): Address {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: Address): AddressDomain =
        AddressDomain(
            city = source.city,
            geoLocation = GeoLocationMapper.mapTo(source.geoLocation),
            neighborhood = source.neighborhood
        )
}