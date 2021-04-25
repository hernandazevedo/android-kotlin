package com.hernandazevedo.zaap.detail.presentation.search

import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.hernandazevedo.zaap.core.base.BaseActivity
import com.hernandazevedo.zaap.core.base.common.DataConstants.IMAGES_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO1_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO2_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO3_EXTRA
import com.hernandazevedo.zaap.core.base.common.DataConstants.INFO4_EXTRA
import com.hernandazevedo.zaap.detail.R
import it.xabaras.android.viewpagerindicator.widget.ViewPagerIndicator

class DetailActivity : BaseActivity() {

    private val info1: TextView by lazy { findViewById(R.id.info1) }
    private val info2: TextView by lazy { findViewById(R.id.info2) }
    private val info3: TextView by lazy { findViewById(R.id.info3) }
    private val info4: TextView by lazy { findViewById(R.id.info4) }
    private val viewPager: ViewPager by lazy { findViewById(R.id.viewPager) }
    private val viewPagerIndicator: ViewPagerIndicator by lazy { findViewById(R.id.viewPagerIndicator) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras?.let {
            info1.text = it.getString(INFO1_EXTRA)
            info2.text = it.getString(INFO2_EXTRA)
            info3.text = it.getString(INFO3_EXTRA)
            info4.text = it.getString(INFO4_EXTRA)
            val images = it.getStringArrayList(IMAGES_EXTRA)
            images?.let { imageList ->
                configureImageSlider(imageList)
            }
        }
    }

    private fun configureImageSlider(imageList: java.util.ArrayList<String>) {
        viewPager.adapter = ViewPagerAdapter(this@DetailActivity, imageList.toList())
        viewPagerIndicator.initWithViewPager(viewPager)
    }
}
