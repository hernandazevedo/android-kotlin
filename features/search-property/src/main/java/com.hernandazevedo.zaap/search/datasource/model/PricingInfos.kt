package com.hernandazevedo.zaap.search.datasource.model

data class PricingInfos(
    val businessType: String,
    val monthlyCondoFee: String? = null,
    val price: String,
    val yearlyIptu: String? = null
)