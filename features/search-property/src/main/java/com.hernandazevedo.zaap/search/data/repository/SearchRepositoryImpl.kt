package com.hernandazevedo.zaap.search.data.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.data.mapper.SearchResponseItemMapper
import com.hernandazevedo.zaap.search.domain.failure.SearchFailure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.SearchRepository
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyBusinessLogic

class SearchRepositoryImpl(private val searchRemoteDataSource: SearchRemoteDataSource) : SearchRepository {
    override suspend fun search(searchPropertyBusinessLogic: SearchPropertyBusinessLogic): Result<List<SearchResponseItemDomain>, Failure> {
        //TODO implement here the cache for the results

        return try {
            Result.Success(
                searchRemoteDataSource.search()
                .map(SearchResponseItemMapper::mapTo)
                //Filter results according to the current search business logic
                .filter(searchPropertyBusinessLogic::filter)
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure(SearchFailure(exception.message ?: ""))
        }
    }
}