package com.hernandazevedo.zaap.detail.presentation.search

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.hernandazevedo.zaap.core.base.BaseActivity
import com.hernandazevedo.zaap.core.base.common.DataConstants.IMAGES_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO1_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO2_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO3_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO4_EXTRA
import com.hernandazevedo.zaap.detail.R

class DetailActivity : BaseActivity() {

    private val info1: TextView by lazy { findViewById(R.id.info1) }
    private val info2: TextView by lazy { findViewById(R.id.info2) }
    private val info3: TextView by lazy { findViewById(R.id.info3) }
    private val info4: TextView by lazy { findViewById(R.id.info4) }
    private val imageView: ImageView by lazy { findViewById(R.id.imageView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras?.let {
            info1.text = it.getString(INFO1_EXTRA)
            info2.text = it.getString(INFO2_EXTRA)
            info3.text = it.getString(INFO3_EXTRA)
            info4.text = it.getString(INFO4_EXTRA)
            val images = it.getStringArrayList(IMAGES_EXTRA)

            //TODO create a view pager
            images?.let { image ->
                val options = RequestOptions()
                    .priority(Priority.HIGH)
                Glide.with(this)
                    .load(image.first())
                    .apply(options)
                    .into(imageView)
            }
        }

    }
}
