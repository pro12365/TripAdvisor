package com.example.tripassistant.ui.models

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.data.models.Places
import java.util.*

//A class must extend this to be shown as recyclerview item
abstract class RecyclerViewItems {

     val uid=UUID.randomUUID().toString()

    companion object {
        const val VIEWTYPE_HEADING = 0
        const val VIEWTYPE_PLACE = 1
        const val VIEWTYPE_SEARCH_RESULT=2
        const val VIEWTYPE_SCROLLABLE_ROW = 3

        fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                VIEWTYPE_HEADING -> Heading.ViewHolder(parent)
                VIEWTYPE_PLACE -> Places.ViewHolder(parent)
                VIEWTYPE_SEARCH_RESULT->SearchResult.ViewHolder(parent)
                else -> Section.ViewHolder(parent) //VIEWTYPE_SCROLLABLE_ROW
            }
    }

    abstract fun getItemViewType(): Int

    abstract fun onBindData(
        viewHolder: RecyclerView.ViewHolder,
    )
    abstract fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean
}