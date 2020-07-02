package com.griddynamics.testwearapp.detail

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.support.wearable.view.CircularButton
import android.widget.TextView
import androidx.wear.widget.drawer.WearableDrawerView
import androidx.wear.widget.drawer.WearableNavigationDrawerView
import com.griddynamics.testwearapp.R
import com.griddynamics.testwearapp.main.ID
import com.griddynamics.testwearapp.main.MainData
import com.griddynamics.testwearapp.stubs

@Suppress("DEPRECATION")
class DetailActivity: WearableActivity() {

    private val navigationItems = arrayListOf(
        ActionData("Overview", android.R.drawable.ic_menu_slideshow),
        ActionData("Map", android.R.drawable.ic_dialog_map)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val itemId = intent.extras?.get(ID) as Int
        val item = stubs.find { it.id == itemId }

        val detailDrawerView = findViewById<WearableDrawerView>(R.id.bottom_drawer)


        val mWearableNavigationDrawer = findViewById<WearableNavigationDrawerView>(R.id.top_navigation_drawer)
        mWearableNavigationDrawer.setAdapter(NavigationAdapter(this, navigationItems))
        mWearableNavigationDrawer.addOnItemSelectedListener {
            setFragment(it, detailDrawerView)
        }

        initItem(item)
        setFragment(0, detailDrawerView)

        findViewById<CircularButton>(R.id.btn_close_button).setOnClickListener { detailDrawerView.controller.closeDrawer() }
    }

    private fun initItem(item: MainData?) {
        findViewById<TextView>(R.id.tv_overview).text = item?.overview
        findViewById<TextView>(R.id.tv_place_name).text = item?.name
        findViewById<TextView>(R.id.tv_header).text = item?.name
    }

    private fun setFragment(pos: Int, drawer: WearableDrawerView) {
        when (pos) {
            0 -> {
                fragmentManager.beginTransaction()
                .replace(R.id.content_frame, FragmentOverview()).commit()
                drawer.controller.peekDrawer()
                drawer.setIsLocked(false)
            }

            1 -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, FragmentMap()).commit()
                drawer.controller.closeDrawer()
                drawer.setIsLocked(true)
            }

            2 -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, FragmentFeedback()).commit()
                drawer.controller.closeDrawer()
                drawer.setIsLocked(true)
            }
        }
    }
}