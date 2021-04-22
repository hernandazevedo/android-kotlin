package com.hernandazevedo.zaap.search.presentation.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

class PaginatedSearchAdapter : ListAdapter<SearchResponseItemDomain, PaginatedSearchViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginatedSearchViewHolder {
        return PaginatedSearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PaginatedSearchViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<SearchResponseItemDomain>() {
            override fun areItemsTheSame(oldItem: SearchResponseItemDomain, newItem: SearchResponseItemDomain): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SearchResponseItemDomain, newItem: SearchResponseItemDomain): Boolean =
                oldItem == newItem
        }
    }
}
