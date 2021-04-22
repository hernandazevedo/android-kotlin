package com.hernandazevedo.zaap.search.domain.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.search.SearchPropertyBusinessLogic
import kotlinx.coroutines.flow.Flow

interface PaginatedSearchRepository {
    suspend fun requestMore(query: SearchPropertyBusinessLogic)
    suspend fun getSearchResultStream(query: SearchPropertyBusinessLogic): Flow<Result<List<SearchResponseItemDomain>, Failure>>
}