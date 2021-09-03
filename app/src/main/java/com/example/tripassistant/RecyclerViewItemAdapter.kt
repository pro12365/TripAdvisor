package com.example.tripassistant

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.ui.models.RecyclerViewItems

class RecyclerViewItemAdapter() :
    ListAdapter<RecyclerViewItems, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewItems.getViewHolder(parent, viewType)
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
                oldItem.uid.equals(newItem.uid)

            override fun areContentsTheSame(
                oldItem: RecyclerViewItems,
                newItem: RecyclerViewItems
            ): Boolean =
                oldItem.areContentsTheSame(newItem)
        }
    }
}