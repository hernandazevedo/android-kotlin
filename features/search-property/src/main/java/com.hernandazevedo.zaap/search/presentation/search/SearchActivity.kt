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

    private val btSearchPrimary: Button by lazy { findViewById(R.id.btSearchPrimary) }
    private val btSearchSecondary: Button by lazy { findViewById(R.id.btSearchSecondary) }
    private val btSearchUseTerms: Button by lazy { findViewById(R.id.btSearchUseTerms) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // TODO analytics tagging
        setupButtons()
        setupListeners()
        observeSearch()
    }

    private fun setupListeners() {
        btSearchUseTerms.setOnClickListener {
            // TODO use terms action
        }
    }

    private fun observeSearch() {
        searchViewModel.run {
            observe(searchResponse, ::handleSearch)
            failure(failure, ::handleFailure)
        }
    }

    private fun handleSearch(searchResponseItemDomain: List<SearchResponseItemDomain>) {
        val i = 0
        searchResponseItemDomain.forEach {
            println("Found element $i")
            println(it.address.city)
            println(it.address.geoLocation)
            println(it.address.neighborhood)
        }
    }

    private fun showLoading(loading: Boolean) {
        //TODO implement the logic to show/ hide the loading
    }

    private fun setupButtons() {
        btSearchPrimary.setupVivaReal()
        btSearchSecondary.setupZap()
    }

    private fun Button.setupVivaReal() {
        text = getString(R.string.search_button_viva_real)
        setOnClickListener {
            //Todo implement search logic for vivaReal
            searchViewModel.searchProperty()
        }
    }

    private fun Button.setupZap() {
        text = getString(R.string.search_button_zap)
        setOnClickListener {
            //Todo implement search logic for zap
            searchViewModel.searchProperty()
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
