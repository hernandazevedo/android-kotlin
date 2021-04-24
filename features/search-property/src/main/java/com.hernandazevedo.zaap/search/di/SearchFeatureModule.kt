package com.hernandazevedo.zaap.search.di

import com.hernandazevedo.zaap.retrofit
import com.hernandazevedo.zaap.search.data.datasource.SearchRemoteDataSource
import com.hernandazevedo.zaap.search.data.repository.PaginatedSearchRepositoryImpl
import com.hernandazevedo.zaap.search.datasource.remote.SearchApi
import com.hernandazevedo.zaap.search.datasource.remote.SearchRemoteDataSourceImpl
import com.hernandazevedo.zaap.search.domain.repository.PaginatedSearchRepository
import com.hernandazevedo.zaap.search.domain.usecase.search.CommonBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.search.VivaRealBusinessLogic
import com.hernandazevedo.zaap.search.domain.usecase.search.ZapBusinessLogic
import com.hernandazevedo.zaap.search.presentation.search.PaginatedSearchAdapter
import com.hernandazevedo.zaap.search.presentation.search.PaginatedSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val SEARCH_API: SearchApi = retrofit.create(SearchApi::class.java)

val searchFeatureModule: Module = module(override = true) {

    single { SEARCH_API }

    single { SearchRemoteDataSourceImpl(searchApi = SEARCH_API) as SearchRemoteDataSource }

    factory { CommonBusinessLogic() }
    factory { VivaRealBusinessLogic(commonBusinessLogic =  get()) }
    factory { ZapBusinessLogic(commonBusinessLogic = get()) }
    factory { PaginatedSearchAdapter(intentFactory = get()) }

    single {
        PaginatedSearchRepositoryImpl(searchRemoteDataSource = get()) as PaginatedSearchRepository
    }

    viewModel {
        PaginatedSearchViewModel(
            paginatedSearchRepository = get(),
            vivaRealBusinessLogic = get(),
            zapBusinessLogic = get()
        )
    }

}
