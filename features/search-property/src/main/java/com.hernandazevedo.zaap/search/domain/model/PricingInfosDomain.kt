package com.hernandazevedo.zaap.search.domain.model

data class PricingInfosDomain(
    val businessType: String,
    val monthlyCondoFee: String? = null,
    val price: String,
    val rentalTotalPrice: String?= null,
    val yearlyIptu: String? = null
)