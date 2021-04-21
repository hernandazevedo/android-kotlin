package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.PricingInfos
import com.hernandazevedo.zaap.search.domain.model.PricingInfosDomain

object PricingInfosMapper: BaseMapper<PricingInfos, PricingInfosDomain>() {
    override fun mapFrom(source: PricingInfosDomain): PricingInfos {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: PricingInfos): PricingInfosDomain {

        return PricingInfosDomain(
            businessType = source.businessType,
            monthlyCondoFee = source.monthlyCondoFee,
            price = source.price,
            rentalTotalPrice = source.rentalTotalPrice,
            yearlyIptu = source.yearlyIptu
        )
    }
}