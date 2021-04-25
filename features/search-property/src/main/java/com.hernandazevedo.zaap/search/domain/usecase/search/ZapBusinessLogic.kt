package com.hernandazevedo.zaap.search.domain.usecase.search

import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.search.ZapBusinessConstants.MIN_USABLE_AREA_PRICE

class ZapBusinessLogic(val commonBusinessLogic: CommonBusinessLogic): SearchPropertyBusinessLogic {

    override fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        /**
         * Ele apenas é elegível pro portal ZAP:
         *  Quando for aluguel e no mínimo o valor for de R$ 3.500,00.
         *  Quando for venda e no mínimo o valor for de R$ 600.000,00.
         */
        return isRentalLogicValid(searchResponseItemDomain) ||
                isSaleLogicValid(searchResponseItemDomain) &&
                isUsableAreasValid(searchResponseItemDomain)
                commonBusinessLogic.filter(searchResponseItemDomain)
    }

    /*
        Caso o imóvel seja para venda, ele é elegível para o portal ZAP se:
        O valor do metro quadrado (chave usableAreas) não pode ser menor/igual a R$ 3.500,00 -
        apenas considerando imóveis que tenham usableAreas acima de 0 (imóveis com usableAreas = 0 não são elegíveis).
     */
    private fun isUsableAreasValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        if (searchResponseItemDomain.pricingInfos.businessType == "SALE") {
            val usableAreas = searchResponseItemDomain.usableAreas

            if (usableAreas > 0) {
                val usableAreaPrice = calculateSquareMetersPrice(searchResponseItemDomain)
                return usableAreaPrice > MIN_USABLE_AREA_PRICE
            }

            return false
        }
        return true
    }

    private fun calculateSquareMetersPrice(searchResponseItemDomain: SearchResponseItemDomain): Int {
        //TODO understand if this logic is correct with the business team
        val price = searchResponseItemDomain.pricingInfos.price.toInt()
        val squareMeters = searchResponseItemDomain.usableAreas
        return (price / squareMeters)
    }

    private fun isRentalLogicValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        val rentalTotalPrice = searchResponseItemDomain.pricingInfos.rentalTotalPrice?.toInt() ?: 0
        return searchResponseItemDomain.pricingInfos.businessType == "RENTAL" &&
                rentalTotalPrice >= ZapBusinessConstants.MIN_RENTAL_PRICE
    }

    private fun isSaleLogicValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean {
        //Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP (descrito abaixo) considere a regra de valor mínimo (do imóvel) 10% menor.
        val minRentalPrice = if(commonBusinessLogic.isBoundingBoxValid(searchResponseItemDomain.address.geoLocation.location))
            (ZapBusinessConstants.MIN_SALE_PRICE * 0.9).toInt()
        else
            ZapBusinessConstants.MIN_SALE_PRICE
        return (searchResponseItemDomain.pricingInfos.businessType == "SALE" &&
                searchResponseItemDomain.pricingInfos.price.toInt()
                >= minRentalPrice)
    }
}