package com.griddynamics.testwearapp.detail

import android.app.Fragment
import android.os.Bundle
import android.support.wearable.view.DotsPageIndicator
import android.support.wearable.view.GridViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.griddynamics.testwearapp.R
import com.griddynamics.testwearapp.main.ID
import com.griddynamics.testwearapp.stubs

class FragmentOverview: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fr_overview, null)
        val itemId = activity.intent.extras?.get(ID) as Int
        val item = stubs.find { it.id == itemId }

        val mGridPager = v.findViewById<GridViewPager>(R.id.gridViewPager)
        val mPagerIndicator = v.findViewById<DotsPageIndicator>(R.id.dotsPageIndicator)
        mGridPager.adapter = GalleryAdapter(inflater.context, item?.images!!)
        mPagerIndicator.setPager(mGridPager)
        return v
    }
}