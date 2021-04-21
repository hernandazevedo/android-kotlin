package com.hernandazevedo.zaap.search.domain.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyBusinessLogic

interface SearchRepository {
    suspend fun search(searchPropertyBusinessLogic: SearchPropertyBusinessLogic): Result<List<SearchResponseItemDomain>, Failure>
}