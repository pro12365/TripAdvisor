package com.example.tripassistant

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.ui.models.RecyclerViewItems

class RecyclerViewItemAdapter() :
    ListAdapter<RecyclerViewItems, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {

    private var eventListener: ((viewHolder: RecyclerView.ViewHolder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = RecyclerViewItems.getViewHolder(parent, viewType)
        eventListener?.invoke(holder)
        return holder
    }

    fun addViewHolderEventListener(eventListener: (viewHolder: RecyclerView.ViewHolder) -> Unit) {
        this.eventListener = eventListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).onBindData(holder)
    }

    override fun getItemViewType(position: Int): Int =
        getItem(position).getItemViewType()

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItems>() {
            override fun areItemsTheSame(
                oldItem: RecyclerViewItems,
                newItem: RecyclerViewItems
            ): Boolean =
                oldItem.uid == newItem.uid

            override fun areContentsTheSame(
                oldItem: RecyclerViewItems,
                newItem: RecyclerViewItems
            ): Boolean =
                oldItem.areContentsTheSame(newItem)
        }
    }
}