package com.hernandazevedo.zaap.search.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hernandazevedo.zaap.core.base.BaseActivity
import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.core.base.extension.failure
import com.hernandazevedo.zaap.core.base.extension.observe
import com.hernandazevedo.zaap.search.R
import com.hernandazevedo.zaap.search.domain.failure.SearchFailure
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val viewModelPaginated: PaginatedSearchViewModel by viewModel()
    private val adapter = PaginatedSearchAdapter()

    private val btSearchVivaReal: Button by lazy { findViewById(R.id.btSearchVivaReal) }
    private val btSearchZap: Button by lazy { findViewById(R.id.btSearchZap) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // TODO analytics tagging
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        setupButtons()
        initAdapter()
        setupScrollListener()
    }

    private fun setupScrollListener() {
        val layoutManager = list.layoutManager as LinearLayoutManager
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModelPaginated.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModelPaginated.run {
            observe(repoResult, ::handleViewModelResult)
            failure(failure, ::handleFailure)
        }
    }

    private fun handleViewModelResult(result: Result<List<SearchResponseItemDomain>, Failure>) {
        when(result){
            is Result.Success -> {
                showEmptyList(result.value.isEmpty())
                adapter.submitList(result.value)
                adapter.notifyDataSetChanged()
            }
        }
    }



    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }

    private fun showLoading(loading: Boolean) {
        //TODO implement the logic to show/ hide the loading
    }

    private fun setupButtons() {
        btSearchVivaReal.setOnClickListener {
            list.scrollToPosition(0)
            viewModelPaginated.searchPropertyVivaReal()
        }

        btSearchZap.setOnClickListener {
            list.scrollToPosition(0)
            viewModelPaginated.searchPropertyZap()
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
