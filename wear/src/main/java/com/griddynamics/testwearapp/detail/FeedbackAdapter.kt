package com.griddynamics.testwearapp.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.griddynamics.testwearapp.R

class FeedbackAdapter(
    mContext: Context,
    private val items: List<FeedbackData>
) : RecyclerView.Adapter<FeedbackAdapter.Holder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        return Holder(mInflater.inflate(R.layout.list_item_fitback, parent, false))
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        if (items.isEmpty()) {
            return
        }
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        private var author: TextView = itemView.findViewById(R.id.tv_feedback_author)
//        private var feedbackMessage: TextView = itemView.findViewById(R.id.tv_feedback_message)

        fun bind(item: FeedbackData) {
            author.text = item.userName
//            feedbackMessage.text = item.feedbackMessage
        }
    }
}