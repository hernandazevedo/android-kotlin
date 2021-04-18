package com.hernandazevedo.zaap.search.domain.usecase

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.UseCase
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.SearchRepository

class SearchPropertyUseCase(private val searchRepository: SearchRepository) :
    UseCase<List<SearchResponseItemDomain>, UseCase.None>() {

    override suspend fun run(params: None): Result<List<SearchResponseItemDomain>, Failure> =
        searchRepository.search()
}