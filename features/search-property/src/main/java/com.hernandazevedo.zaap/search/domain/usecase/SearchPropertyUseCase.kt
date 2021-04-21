package com.hernandazevedo.zaap.search.domain.usecase

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.UseCase
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.SearchRepository

class SearchPropertyUseCase(private val searchRepository: SearchRepository) :
    UseCase<List<SearchResponseItemDomain>, SearchPropertyUseCase.Params>() {

    override suspend fun run(params: Params): Result<List<SearchResponseItemDomain>, Failure> =
        searchRepository.search(params.searchPropertyBusinessLogic)

    data class Params(val searchPropertyBusinessLogic: SearchPropertyBusinessLogic)
}