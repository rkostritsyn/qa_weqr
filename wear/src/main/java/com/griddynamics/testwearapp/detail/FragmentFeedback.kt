package com.griddynamics.testwearapp.detail

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.griddynamics.testwearapp.R
import com.griddynamics.testwearapp.ScalingScrollLayoutCallback
import com.griddynamics.testwearapp.main.MainAdapter
import com.griddynamics.testwearapp.stubFeedBack
import com.griddynamics.testwearapp.stubs

class FragmentFeedback: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater!!.inflate(R.layout.fr_feedback, null)
//        val adapter = FeedbackAdapter(activity, stubFeedBack)
//
//        val recyclerView: WearableRecyclerView = v.findViewById(R.id.feedbakc_recycler_view)
//        recyclerView.layoutManager =
//            WearableLinearLayoutManager(activity, ScalingScrollLayoutCallback())
//        recyclerView.isEdgeItemsCenteringEnabled = true
//        recyclerView.adapter = adapter
       return v
    }
}