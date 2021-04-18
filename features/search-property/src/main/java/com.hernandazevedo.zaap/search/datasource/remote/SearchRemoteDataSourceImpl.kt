package com.hernandazevedo.zaap.search.datasource.remote

import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.datasource.model.SearchResponseItem

class SearchRemoteDataSourceImpl(private val searchApi: SearchApi): SearchRemoteDataSource {
    override suspend fun search(): List<SearchResponseItem> =
        searchApi.searchAsync().await()
}