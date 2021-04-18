package com.hernandazevedo.zaap.search.domain.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

interface SearchRepository {
    suspend fun search(): Result<List<SearchResponseItemDomain>, Failure>
}