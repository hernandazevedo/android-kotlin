package com.hernandazevedo.zaap.search.domain.usecase.search

object BoundingBoxZap {
    val MINLON = -46.693419
    val MINLAT = -23.568704
    val MAXLON = -46.641146
    val MAXLAT = -23.546686
}

object ZapBusinessConstants {
    val MIN_RENTAL_PRICE = 3500
    val MIN_SALE_PRICE = 600000
    val MIN_USABLE_AREA_PRICE = 3500
}

object VivaRealBusinessConstants {
    val MAX_RENTAL_PRICE = 4000
    val MAX_SALE_PRICE = 700000
}

object CommonConstants {
    val BUSINESS_TYPE_SALE = "SALE"
    val BUSINESS_TYPE_RENTAL = "RENTAL"
}