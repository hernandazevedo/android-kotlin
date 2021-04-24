package com.hernandazevedo.zaap.search.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.hernandazevedo.zaap.core.base.navigation.IntentFactory
import com.hernandazevedo.zaap.core.base.navigation.IntentFactory.Companion.ACTION_DETAIL
import com.hernandazevedo.zaap.search.R
import com.hernandazevedo.zaap.search.domain.model.SearchResponseItemDomain

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class PaginatedSearchViewHolder(view: View, private val intentFactory: IntentFactory) :
    RecyclerView.ViewHolder(view) {
    private val info1: TextView = view.findViewById(R.id.info1)
    private val info2: TextView = view.findViewById(R.id.info2)
    private val info3: TextView = view.findViewById(R.id.info3)
    private val info4: TextView = view.findViewById(R.id.info4)

    private val imageView: ImageView = view.findViewById(R.id.imageView)

    private var domain: SearchResponseItemDomain? = null

    init {
        view.setOnClickListener {
            domain?.id?.let { id ->
                val intent = intentFactory.getIntent(ACTION_DETAIL, mapOf("id" to id))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(item: SearchResponseItemDomain?) {
        if (item == null) {
            val resources = itemView.resources
            info1.text = resources.getString(R.string.loading)
            info2.text = resources.getString(R.string.loading)
            info3.text = resources.getString(R.string.loading)
        } else {
            showData(item)
        }
    }

    private fun showData(item: SearchResponseItemDomain) {
        val resources = itemView.resources
        this.domain = item
        info1.text = item.address.city
        info2.text = "${resources.getString(R.string.bedrooms, item.bedrooms.toString())} | " +
                "${
                    resources.getString(
                        R.string.bathrooms,
                        item.bathrooms.toString()
                    )
                } | ${resources.getString(R.string.usablearea, item.usableAreas.toString())} | " +
                "${resources.getString(R.string.parkingspace, item.parkingSpaces.toString())}"
        info3.text = "${
            resources.getString(
                R.string.price,
                item.pricingInfos.price
            )
        }  | ${
            resources.getString(
                R.string.price_rental,
                item.pricingInfos.rentalTotalPrice ?: ""
            )
        } "
        info4.text = "${resources.getString(R.string.code, item.id)}"

        val options = RequestOptions()
            .priority(Priority.HIGH)
        Glide.with(itemView)
            .load(item.images.first())
            .apply(options)
            .into(imageView)


    }

    companion object {
        fun create(parent: ViewGroup, intentFactory: IntentFactory): PaginatedSearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_view_item, parent, false)
            return PaginatedSearchViewHolder(view, intentFactory)
        }
    }
}
