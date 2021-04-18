package com.hernandazevedo.zaap.search.data.repository

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.data.mapper.SearchResponseItemMapper
import com.hernandazevedo.zaap.search.domain.failure.SearchFailure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.SearchRepository

class SearchRepositoryImpl(private val searchRemoteDataSource: SearchRemoteDataSource) : SearchRepository {
    override suspend fun search(): Result<List<SearchResponseItemDomain>, Failure> {
        return try {
            Result.Success(
                SearchResponseItemMapper.mapToList(
                    searchRemoteDataSource.search()
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure(SearchFailure(exception.message ?: ""))
        }
    }
}