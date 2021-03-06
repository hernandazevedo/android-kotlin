package com.hernandazevedo.zaap.search.data.repository

import android.util.Log
import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.data.mapper.SearchResponseItemMapper
import com.hernandazevedo.zaap.search.domain.failure.SearchFailure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.PaginatedSearchRepository
import com.hernandazevedo.zaap.search.domain.usecase.search.SearchPropertyBusinessLogic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.*

private const val STARTING_PAGE_INDEX = 1

/**
 * Repository class that works with local and remote data sources.
 */
class PaginatedSearchRepositoryImpl(private val searchRemoteDataSource: SearchRemoteDataSource) :
        PaginatedSearchRepository {

    // keep the list of all results received to accumulate results for pagination
    private val inMemoryCache = mutableListOf<SearchResponseItemDomain>()

    // keep the list of all results received to request the service only one time
    private val inMemoryCacheRaw = mutableListOf<SearchResponseItemDomain>()

    // shared flow of results, which allows us to broadcast updates so
    // the subscriber will have the latest data
    private val searchResults = MutableSharedFlow<Result<List<SearchResponseItemDomain>, Failure>>(
            replay = 1
    )

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = STARTING_PAGE_INDEX

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    override suspend fun getSearchResultStream(query: SearchPropertyBusinessLogic): Flow<Result<List<SearchResponseItemDomain>, Failure>> {
        lastRequestedPage = 1
        inMemoryCache.clear()
        if (requestAndSaveData(query))
            lastRequestedPage++

        return searchResults
    }

    override suspend fun requestMore(query: SearchPropertyBusinessLogic) {
        if (isRequestInProgress) return
        val successful = requestAndSaveData(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    suspend fun retry(query: SearchPropertyBusinessLogic) {
        if (isRequestInProgress) return
        requestAndSaveData(query)
    }

    private suspend fun requestAndSaveData(query: SearchPropertyBusinessLogic): Boolean {
        isRequestInProgress = true
        var successful = false

        try {

            if (inMemoryCacheRaw.isEmpty()) {
                val response = searchRemoteDataSource.searchAsync()
                        .map(SearchResponseItemMapper::mapTo)
                inMemoryCacheRaw.addAll(response)
            }

            val paginatedList = getPage(inMemoryCacheRaw, lastRequestedPage, NETWORK_PAGE_SIZE)

            inMemoryCache.addAll(paginatedList)

            val itemsByQuery = findByQuery(query)
            searchResults.emit(Result.Success(itemsByQuery))

            successful = true
        } catch (exception: Exception) {
            searchResults.emit(Result.Failure(SearchFailure(exception.message ?: "")))
        }
        isRequestInProgress = false
        return successful
    }

    private fun findByQuery(query: SearchPropertyBusinessLogic): List<SearchResponseItemDomain> {
        return inMemoryCache.filter(query::filter)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    private fun <T> getPage(sourceList: List<T>?, page: Int, pageSize: Int): List<T> {
        require(!(pageSize <= 0 || page <= 0)) { "invalid page size: $pageSize" }
        val fromIndex = (page - 1) * pageSize
        return if (sourceList == null || sourceList.size <= fromIndex) {
            Collections.emptyList()
        } else sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size))
    }
}
