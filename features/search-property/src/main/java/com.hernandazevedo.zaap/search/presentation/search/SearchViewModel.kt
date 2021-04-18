package com.hernandazevedo.zaap.search.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hernandazevedo.zaap.core.base.BaseViewModel
import com.hernandazevedo.zaap.core.base.UseCase
import com.hernandazevedo.zaap.core.base.common.ResourceManager
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyUseCase

class SearchViewModel(
    private val searchPropertyUseCase: SearchPropertyUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _searchResponse = MutableLiveData<List<SearchResponseItemDomain>>()
    val searchResponse: LiveData<List<SearchResponseItemDomain>>
        get() = _searchResponse

    private val _userAlreadyLaunched = MutableLiveData<Boolean>()
    val userAlreadyLaunched: LiveData<Boolean>
        get() = _userAlreadyLaunched

    fun searchProperty() {
        searchPropertyUseCase(UseCase.None()) {
            it.either(::handleSearchSuccess, ::handleFailure)
        }
    }

    private fun handleSearchSuccess(searchResponseItemDomain: List<SearchResponseItemDomain>) {
        _searchResponse.value = searchResponseItemDomain
    }
}
