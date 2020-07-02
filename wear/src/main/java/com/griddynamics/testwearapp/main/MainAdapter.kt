package com.griddynamics.testwearapp.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.griddynamics.testwearapp.R
import com.griddynamics.testwearapp.detail.DetailActivity

const val ID = "ID"

class MainAdapter(
    private val mContext: Context,
    items: List<MainData>
) : RecyclerView.Adapter<MainAdapter.Holder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)
    private val mItems: List<MainData> = items

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        return Holder(mInflater.inflate(R.layout.list_item_main, parent, false))
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        if (mItems.isEmpty()) {
            return
        }
        val item = mItems[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            mContext.startActivity(Intent(mContext, DetailActivity::class.java).putExtra(ID, item.id))  }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class Holder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        private var mTextView: TextView = itemView.findViewById(R.id.icon_text_view)
//        private var mImageView: ImageView = itemView.findViewById(R.id.icon_image_view)

        fun bind(item: MainData) {
            mTextView.text = item.name
//            mImageView.setImageResource(item.iconId)
        }
    }
}
