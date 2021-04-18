package com.hernandazevedo.zaap.search.data.datasource

import com.hernandazevedo.zaap.search.datasource.model.SearchResponseItem

interface SearchRemoteDataSource {
    suspend fun search(): List<SearchResponseItem>
}