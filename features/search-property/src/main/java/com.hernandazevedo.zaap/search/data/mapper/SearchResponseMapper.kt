package com.hernandazevedo.zaap.search.data.mapper

import com.hernandazevedo.zaap.core.base.BaseMapper
import com.hernandazevedo.zaap.search.datasource.model.SearchResponse
import com.hernandazevedo.zaap.search.domain.model.SearchResponseDomain

object SearchResponseMapper : BaseMapper<SearchResponse, SearchResponseDomain>() {
    override fun mapFrom(source: SearchResponseDomain): SearchResponse {
        throw UnsupportedOperationException("Method not implemented")
    }

    override fun mapTo(source: SearchResponse): SearchResponseDomain {
        val domainResponse = SearchResponseDomain()

        source.forEach {
            domainResponse.add(SearchResponseItemMapper.mapTo(it))
        }

        return domainResponse
    }


}