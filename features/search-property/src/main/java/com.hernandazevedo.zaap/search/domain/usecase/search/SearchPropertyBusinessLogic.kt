package com.hernandazevedo.zaap.search.domain.usecase.search

import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

interface SearchPropertyBusinessLogic {
    fun filter(searchResponseItemDomain: SearchResponseItemDomain): Boolean
}