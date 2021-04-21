package com.hernandazevedo.zaap.search.domain.usecase

import com.hernandazevedo.zaap.search.domain.model.LocationDomain
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.BoundingBoxZap.MAXLAT
import com.hernandazevedo.zaap.search.domain.usecase.BoundingBoxZap.MAXLON
import com.hernandazevedo.zaap.search.domain.usecase.BoundingBoxZap.MINLAT
import com.hernandazevedo.zaap.search.domain.usecase.BoundingBoxZap.MINLON

class CommonBusinessLogic: SearchPropertyBusinessLogic {
    override fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean =
        isLocationValid(searchResponseItemDomain) &&
        isBoundingBoxValid(searchResponseItemDomain.address.geoLocation.location)

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
    private fun isBoundingBoxValid(location: LocationDomain) =
        location.lat > MINLAT &&
                location.lat < MAXLAT &&
                location.lon > MINLON &&
                location.lon < MAXLON

}