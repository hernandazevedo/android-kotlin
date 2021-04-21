package com.hernandazevedo.zaap.search.domain.usecase.logic

import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

class ZapBusinessLogic(val commonBusinessLogic: CommonBusinessLogic): SearchPropertyBusinessLogic {

    override fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean{
        /**
         * Ele apenas é elegível pro portal ZAP:
         *  Quando for aluguel e no mínimo o valor for de R$ 3.500,00.
         *  Quando for venda e no mínimo o valor for de R$ 600.000,00.
         */
        return isRentalLogicValid(searchResponseItemDomain) &&
                isSaleLogicValid(searchResponseItemDomain) &&
                commonBusinessLogic.filter(searchResponseItemDomain)
    }

    private fun isRentalLogicValid(searchResponseItemDomain: SearchResponseItemDomain) =
        (searchResponseItemDomain.pricingInfos.businessType == "RENTAL" &&
                (searchResponseItemDomain.pricingInfos.rentalTotalPrice?.toInt()
                    ?: 0) >= ZapBusinessConstants.MIN_RENTAL_PRICE)

    private fun isSaleLogicValid(searchResponseItemDomain: SearchResponseItemDomain) =
        (searchResponseItemDomain.pricingInfos.businessType == "SALE" &&
                searchResponseItemDomain.pricingInfos.price.toInt()
                     >= ZapBusinessConstants.MIN_SALE_PRICE)
}