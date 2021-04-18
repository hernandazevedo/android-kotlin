package com.hernandazevedo.zaap.search.domain.model

data class SearchResponseItemDomain(
    val address: AddressDomain,
    val bathrooms: Int,
    val bedrooms: Int,
    val createdAt: String,
    val id: String,
    val images: List<String>,
    val listingStatus: String,
    val listingType: String,
    val owner: Boolean,
    val parkingSpaces: Int,
    val pricingInfos: PricingInfosDomain,
    val updatedAt: String,
    val usableAreas: Int
)