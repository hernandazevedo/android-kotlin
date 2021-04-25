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
            val usableAreas = searchResponseItemDomain.usableAreas

            //FIXME How to calculate the square meter value? it is not written in the business document
            if(usableAreas > 0) {
                val usableAreaPrice = calculateUsableAreaPrice(searchResponseItemDomain)
                return usableAreaPrice > MIN_USABLE_AREA_PRICE
            }

        return false
    }

    private fun calculateUsableAreaPrice(searchResponseItemDomain: SearchResponseItemDomain): Int {
        //FIXME this logic is wrong, we need to understand how to calculate the square meter value
        return MIN_USABLE_AREA_PRICE + 1
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