package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.SearchResponseItem
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

object SearchResponseItemMapper : BaseMapper<SearchResponseItem, SearchResponseItemDomain>() {
    override fun mapFrom(source: SearchResponseItemDomain): SearchResponseItem {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: SearchResponseItem): SearchResponseItemDomain =
        SearchResponseItemDomain(
            address = AddressMapper.mapTo(source.address),
            bathrooms = source.bathrooms,
            bedrooms = source.bedrooms,
            createdAt = source.createdAt,
            id = source.id,
            images = source.images,
            listingStatus = source.listingStatus,
            listingType = source.listingType,
            owner = source.owner,
            parkingSpaces = source.parkingSpaces,
            pricingInfos = PricingInfosMapper.mapTo(source.pricingInfos),
            updatedAt = source.updatedAt,
            usableAreas = source.usableAreas
        )
}