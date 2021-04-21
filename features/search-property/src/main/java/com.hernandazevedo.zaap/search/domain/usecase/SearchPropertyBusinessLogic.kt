package com.hernandazevedo.zaap.search.domain.usecase

import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

interface SearchPropertyBusinessLogic {
    fun isValid(searchResponseItemDomain: SearchResponseItemDomain): Boolean
}