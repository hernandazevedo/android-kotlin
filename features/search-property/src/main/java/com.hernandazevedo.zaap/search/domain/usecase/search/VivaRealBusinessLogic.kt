package com.hernandazevedo.zaap.search.domain.usecase.search

import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

class VivaRealBusinessLogic(val commonBusinessLogic: CommonBusinessLogic):
    SearchPropertyBusinessLogic {
    override fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        /**
         *  Ele apenas é elegível pro portal Viva Real:
            Quando for aluguel e no máximo o valor for de R$ 4.000,00.
            Quando for venda e no máximo o valor for de R$ 700.000,00.
            Onde:
         *
         */
        return isRentalLogicValid(searchResponseItemDomain) ||
                isSaleLogicValid(searchResponseItemDomain) &&
                commonBusinessLogic.filter(searchResponseItemDomain)
    }

    private fun isRentalLogicValid(searchResponseItemDomain: SearchResponseItemDomain) =
        (searchResponseItemDomain.pricingInfos.businessType == "RENTAL" &&
                (searchResponseItemDomain.pricingInfos.rentalTotalPrice?.toInt()
                    ?: 0) <= VivaRealBusinessConstants.MAX_RENTAL_PRICE)

    private fun isSaleLogicValid(searchResponseItemDomain: SearchResponseItemDomain) =
        (searchResponseItemDomain.pricingInfos.businessType == "SALE" &&
                searchResponseItemDomain.pricingInfos.price.toInt()
                <= VivaRealBusinessConstants.MAX_SALE_PRICE)

}