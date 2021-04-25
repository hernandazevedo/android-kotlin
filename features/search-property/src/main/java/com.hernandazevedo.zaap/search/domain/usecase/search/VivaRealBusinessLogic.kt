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
                isMontlyCondoFeeValid(searchResponseItemDomain) &&
                commonBusinessLogic.filter(searchResponseItemDomain)
    }

    /**
     * Caso o imóvel seja para aluguel, ele é elegível para o portal Viva Real se:
    O valor do condomínio não pode ser maior/igual que 30% do valor do aluguel -
    apenas aplicado para imóveis que tenham um monthlyCondoFee válido e numérico
    (imóveis com monthlyCondoFee não numérico ou inválido não são elegíveis).
     */
    private fun isMontlyCondoFeeValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        if(searchResponseItemDomain.pricingInfos.businessType == "RENTAL") {
            try {
                val rentalTotalPrice =
                    searchResponseItemDomain.pricingInfos.rentalTotalPrice?.toLong()
                val monthlyCondoFee =
                    searchResponseItemDomain.pricingInfos.monthlyCondoFee?.toLong()
                if (rentalTotalPrice != null && monthlyCondoFee != null) {
                    if (monthlyCondoFee < (rentalTotalPrice * 0.3)
                    )
                        return true
                }
            } catch (e: Exception) {
                return false
            }
            return false
        }

        return true
    }

    private fun isRentalLogicValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean {

        //Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP (descrito abaixo) considere a regra de valor máximo (do aluguel do imóvel) 50% maior
        val maxRentalPrice = if(commonBusinessLogic.isBoundingBoxValid(searchResponseItemDomain.address.geoLocation.location))
            (VivaRealBusinessConstants.MAX_RENTAL_PRICE * 1.5).toInt()
        else
            VivaRealBusinessConstants.MAX_RENTAL_PRICE

        return (searchResponseItemDomain.pricingInfos.businessType == "RENTAL" &&
                (searchResponseItemDomain.pricingInfos.rentalTotalPrice?.toInt()
                    ?: 0) <= maxRentalPrice)
    }

    private fun isSaleLogicValid(searchResponseItemDomain: SearchResponseItemDomain) =
        (searchResponseItemDomain.pricingInfos.businessType == "SALE" &&
                searchResponseItemDomain.pricingInfos.price.toInt()
                <= VivaRealBusinessConstants.MAX_SALE_PRICE)

}