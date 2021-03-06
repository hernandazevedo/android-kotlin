package com.hernandazevedo.zaap.search.domain.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.search.SearchPropertyBusinessLogic
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchPropertyBusinessLogic: SearchPropertyBusinessLogic): Flow<Result<List<SearchResponseItemDomain>, Failure>>
}