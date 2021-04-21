package com.hernandazevedo.zaap.search.presentation.search

import android.os.Bundle
import android.widget.Button
import com.hernandazevedo.zaap.core.base.BaseActivity
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.core.base.extension.failure
import com.hernandazevedo.zaap.core.base.extension.observe
import com.hernandazevedo.zaap.search.R
import com.hernandazevedo.zaap.search.domain.failure.SearchFailure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    private val btSearchVivaReal: Button by lazy { findViewById(R.id.btSearchVivaReal) }
    private val btSearchZap: Button by lazy { findViewById(R.id.btSearchZap) }
    private val btSearchUseTerms: Button by lazy { findViewById(R.id.btSearchUseTerms) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // TODO analytics tagging
        setupButtons()
        observeSearch()
    }

    private fun observeSearch() {
        searchViewModel.run {
            observe(searchResponse, ::handleSearch)
            failure(failure, ::handleFailure)
        }
    }

    private fun handleSearch(searchResponseItemDomain: List<SearchResponseItemDomain>) {
        var i = 0
        searchResponseItemDomain.forEach {
            println("###################### Found element ${i++} ##########################")
            println("id ${it.id}")
            println("address.city ${it.address.city}")
            println("address.geoLocation ${it.address.geoLocation}")
            println("address.neighborhood ${it.address.neighborhood}")
            println("###################### End ##########################")
        }
    }

    private fun showLoading(loading: Boolean) {
        //TODO implement the logic to show/ hide the loading
    }

    private fun setupButtons() {
        btSearchVivaReal.setOnClickListener {
            searchViewModel.searchPropertyVivaReal()
        }

        btSearchZap.setOnClickListener {
            searchViewModel.searchPropertyZap()
        }
    }

    private fun handleFailure(failure: Failure) {
        showLoading(false)
        when (failure) {
            is Failure.NetworkConnection -> showMessage(R.string.failure_network_connection)
            is Failure.ServerError -> showMessage(R.string.failure_server_error)
            is SearchFailure -> showMessage(
                R.string.failure_search_error,
                failure.message
            )
        }
    }
}
