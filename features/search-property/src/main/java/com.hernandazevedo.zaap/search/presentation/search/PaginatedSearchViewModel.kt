
package com.hernandazevedo.zaap.search.presentation.search

import androidx.lifecycle.*
import com.hernandazevedo.zaap.core.base.BaseViewModel
import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.search.data.repository.PaginatedSearchRepositoryImpl
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.repository.PaginatedSearchRepository
import com.hernandazevedo.zaap.search.domain.usecase.search.SearchPropertyBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.search.VivaRealBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.search.ZapBusinessLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [PaginatedSearchRepositoryImpl] to get the data.
 */
class PaginatedSearchViewModel(private val paginatedSearchRepository: PaginatedSearchRepository,
                               private val vivaRealBusinessLogic: VivaRealBusinessLogic,
                               private val zapBusinessLogic: ZapBusinessLogic
) : BaseViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<SearchPropertyBusinessLogic>()

    val repoResult: LiveData<Result<List<SearchResponseItemDomain>, Failure>> = queryLiveData.switchMap { query ->
        liveData {
            val result = paginatedSearchRepository.getSearchResultStream(query).asLiveData(Dispatchers.Main)
            emitSource(result)
        }
    }

    fun searchPropertyZap() {
        searchRepo(zapBusinessLogic)
    }

    fun searchPropertyVivaReal() {
        searchRepo(vivaRealBusinessLogic)
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(query: SearchPropertyBusinessLogic) {
        queryLiveData.postValue(query)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = queryLiveData.value
            if (immutableQuery != null) {
                viewModelScope.launch {
                    paginatedSearchRepository.requestMore(immutableQuery)
                }
            }
        }
    }
}
