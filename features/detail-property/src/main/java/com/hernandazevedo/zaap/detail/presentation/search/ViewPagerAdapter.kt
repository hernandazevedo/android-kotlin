package com.hernandazevedo.zaap.detail.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.hernandazevedo.zaap.detail.R

class ViewPagerAdapter(private val context: Context, private val images: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.view_pager_layout, null)
        val imageView = view.findViewById<View>(R.id.imageView) as ImageView
        val options = RequestOptions()
            .priority(Priority.HIGH)
        Glide.with(context)
            .load(images[position])
            .apply(options)
            .into(imageView)
        val vp: ViewPager = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val vp: ViewPager = container as ViewPager
        val view = obj as View
        vp.removeView(view)
    }
}