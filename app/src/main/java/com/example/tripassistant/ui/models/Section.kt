package com.example.tripassistant.ui.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.databinding.ExploreRowBinding
import com.example.tripassistant.ui.explore.ChildItemAdapter

//Denotes a section with horizontally scrollable child items
data class Section(
    val heading: String,
    val childItems: List<RecyclerViewItems>
) : RecyclerViewItems() {
    override fun getItemViewType(): Int = VIEWTYPE_SCROLLABLE_ROW

    override fun onBindData(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as ViewHolder).bind(this)
    }

    override fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean =
        if (recyclerViewItems is Section) heading == recyclerViewItems.heading
                && childItems == recyclerViewItems.childItems else false

    class ViewHolder private constructor(private val binding: ExploreRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            ExploreRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(section: Section) {
            binding.apply {
                recyclerView.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = ChildItemAdapter(section.childItems)
                headerTitle.text = section.heading
            }
        }
    }

}
