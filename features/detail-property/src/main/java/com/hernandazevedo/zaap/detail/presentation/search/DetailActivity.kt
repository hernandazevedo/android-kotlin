package com.hernandazevedo.zaap.detail.presentation.search

import android.os.Bundle
import android.widget.TextView
import com.hernandazevedo.zaap.core.base.BaseActivity
import com.hernandazevedo.zaap.detail.R

class DetailActivity : BaseActivity() {

    private val info1: TextView by lazy { findViewById(R.id.info1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.extras?.getString("id")
        info1.text = "${resources.getString(R.string.code, id)}"

    }
}
