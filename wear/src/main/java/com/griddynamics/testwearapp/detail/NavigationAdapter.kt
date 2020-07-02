package com.griddynamics.testwearapp.detail

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.wear.widget.drawer.WearableNavigationDrawerView

class NavigationAdapter(
    private val context: Context,
    private val items: List<ActionData>
): WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItemText(pos: Int): String? {
        return items[pos].name
    }

    override fun getItemDrawable(pos: Int): Drawable? {
        return context.getDrawable(items[pos].iconId)
    }
}