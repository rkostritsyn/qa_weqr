package com.griddynamics.testwearapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager.LayoutCallback
import kotlin.math.min

class ScalingScrollLayoutCallback: LayoutCallback() {

    private val MAX_ICON_PROGRESS = 0.65f

    override fun onLayoutFinished(child: View?, parent: RecyclerView?) {

        val centerOffset = child!!.height / 2.0f / parent!!.height
        val yRelativeToCenterOffset =
            child.y / parent.height + centerOffset

        var progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset)

        progressToCenter = min(progressToCenter, MAX_ICON_PROGRESS)

        child.scaleX = 1 - progressToCenter
        child.scaleY = 1 - progressToCenter
    }

}