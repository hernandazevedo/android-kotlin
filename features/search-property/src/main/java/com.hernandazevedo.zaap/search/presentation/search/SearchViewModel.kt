package com.hernandazevedo.zaap.search.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hernandazevedo.zaap.core.base.BaseViewModel
import com.hernandazevedo.zaap.core.base.common.ResourceManager
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyUseCase
import com.hernandazevedo.zaap.search.domain.usecase.VivaRealBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.ZapBusinessLogic

class SearchViewModel(
    private val searchPropertyUseCase: SearchPropertyUseCase,
    private val resourceManager: ResourceManager,
    private val vivaRealBusinessLogic: VivaRealBusinessLogic,
    private val zapBusinessLogic: ZapBusinessLogic
) : BaseViewModel() {

    private val _searchResponse = MutableLiveData<List<SearchResponseItemDomain>>()
    val searchResponse: LiveData<List<SearchResponseItemDomain>>
        get() = _searchResponse

    fun searchPropertyZap() {
        searchProperty(zapBusinessLogic)
    }

    fun searchPropertyVivaReal() {
        searchProperty(vivaRealBusinessLogic)
    }

    private fun searchProperty(searchPropertyBusinessLogic: SearchPropertyBusinessLogic) {
        searchPropertyUseCase(SearchPropertyUseCase.Params(searchPropertyBusinessLogic)) {
            it.either(::handleSearchSuccess, ::handleFailure)
        }
    }

    private fun handleSearchSuccess(searchResponseItemDomain: List<SearchResponseItemDomain>) {
        //Filter results using the business logic
        _searchResponse.value = searchResponseItemDomain
    }
}
