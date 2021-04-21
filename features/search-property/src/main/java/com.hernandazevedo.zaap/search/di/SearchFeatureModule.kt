package com.hernandazevedo.zaap.search.di

import com.hernandazevedo.zaap.retrofit
import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.data.repository.SearchRepositoryImpl
import com.hernandazevedo.zaap.search.datasource.remote.SearchApi
import com.hernandazevedo.zaap.search.datasource.remote.SearchRemoteDataSourceImpl
import com.hernandazevedo.zaap.search.domain.repository.SearchRepository
import com.hernandazevedo.zaap.search.domain.usecase.CommonBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.SearchPropertyUseCase
import com.hernandazevedo.zaap.search.presentation.search.SearchViewModel
import com.hernandazevedo.zaap.search.domain.usecase.VivaRealBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.ZapBusinessLogic
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val SEARCH_API: SearchApi = retrofit.create(SearchApi::class.java)

val searchFeatureModule: Module = module(override = true) {

    single { SEARCH_API }

    single { SearchRemoteDataSourceImpl(searchApi = SEARCH_API) as SearchRemoteDataSource }

    single { SearchRepositoryImpl(searchRemoteDataSource = get()) as SearchRepository }

    factory { SearchPropertyUseCase(searchRepository = get()) }

    factory { CommonBusinessLogic() }
    factory { VivaRealBusinessLogic(commonBusinessLogic =  get()) }
    factory { ZapBusinessLogic(commonBusinessLogic = get()) }

    viewModel {
        SearchViewModel(
            searchPropertyUseCase = get(),
            resourceManager = get(),
            vivaRealBusinessLogic = get(),
            zapBusinessLogic = get()
        )
    }

}
