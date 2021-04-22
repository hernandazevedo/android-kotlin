package com.hernandazevedo.zaap.search.datasource.remote

import com.hernandazevedo.zaap.search.datasource.model.SearchResponseItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

const val SEARCH_ENDPOINT = "/sources/source-1.json"

interface SearchApi {

    @GET(SEARCH_ENDPOINT)
    fun searchAsync(): Deferred<List<SearchResponseItem>>

    @GET(SEARCH_ENDPOINT)
    fun search(): List<SearchResponseItem>
}
