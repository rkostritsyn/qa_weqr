package com.griddynamics.testwearapp.detail

import android.content.Context
import android.support.wearable.view.GridPagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.griddynamics.testwearapp.R


class GalleryAdapter(
    private val mContext: Context,
    private val images: List<Int>
) : GridPagerAdapter() {

    override fun getRowCount(): Int {
        return 1
    }

    override fun getColumnCount(rowNum: Int): Int {
        return images.size
    }

    override fun getCurrentColumnForRow(row: Int, currentColumn: Int): Int {
        return currentColumn
    }

    override fun instantiateItem(viewGroup: ViewGroup, row: Int, col: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setBackgroundColor(mContext.resources.getColor(R.color.white))
        imageView.setImageResource(images[col])
        viewGroup.addView(imageView)
        return imageView
    }

    override fun destroyItem(viewGroup: ViewGroup, i: Int, i1: Int, `object`: Any?) {
        viewGroup.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any?): Boolean {
        return view.equals(`object`)
    }

}