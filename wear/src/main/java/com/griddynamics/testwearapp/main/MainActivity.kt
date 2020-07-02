package com.griddynamics.testwearapp.main

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.griddynamics.testwearapp.R
import com.griddynamics.testwearapp.ScalingScrollLayoutCallback
import com.griddynamics.testwearapp.stubs

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(this, stubs)

        val recyclerView: WearableRecyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager =
            WearableLinearLayoutManager(this, ScalingScrollLayoutCallback())
        recyclerView.isEdgeItemsCenteringEnabled = true
        recyclerView.adapter = adapter
    }
}