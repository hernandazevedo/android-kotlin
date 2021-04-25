package com.hernandazevedo.zaap.core.base.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle

class IntentFactory(private val context: Context) {

    companion object {
        const val ACTION_SEARCH = "action.search.zaap"
        const val ACTION_DETAIL = "action.detail.zaap"
    }

    fun getIntent(action: String, data: Map<String, String>? = null): Intent {
        return Intent(action).apply {
            setPackage(context.packageName)
            data?.let { unwrappedData ->
                unwrappedData.forEach {
                    this.putExtra(it.key, it.value)
                }
            }
        }
    }

    fun getIntent(action: String, bundle: Bundle? = null): Intent {
        return Intent(action).apply {
            setPackage(context.packageName)
            bundle?.let { unwrappedData ->
                this.putExtras(unwrappedData);
            }
        }
    }

}
