package com.hernandazevedo.zaap.search.domain.usecase.search

import com.hernandazevedo.zaap.search.domain.model.LocationDomain
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.search.BoundingBoxZap.MAXLAT
import com.hernandazevedo.zaap.search.domain.usecase.search.BoundingBoxZap.MAXLON
import com.hernandazevedo.zaap.search.domain.usecase.search.BoundingBoxZap.MINLAT
import com.hernandazevedo.zaap.search.domain.usecase.search.BoundingBoxZap.MINLON

class CommonBusinessLogic: SearchPropertyBusinessLogic {
    override fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean =
        isLocationValid(searchResponseItemDomain)

    /**
     * Um imóvel não é elegível em NENHUM PORTAL se:
        Ele tem lat e lon iguais a 0.
     */
    private fun isLocationValid(searchResponseItemDomain: SearchResponseItemDomain) =
        searchResponseItemDomain.address.geoLocation.location.lat != 0.0 &&
                searchResponseItemDomain.address.geoLocation.location.lon != 0.0

    /**
     * Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP
     */
    fun isBoundingBoxValid(location: LocationDomain) =
        location.lat > MINLAT &&
                location.lat < MAXLAT &&
                location.lon > MINLON &&
                location.lon < MAXLON

}