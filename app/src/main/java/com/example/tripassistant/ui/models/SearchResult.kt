package com.example.tripassistant.ui.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.data.models.Places
import com.example.tripassistant.databinding.SuggestionLayoutBinding

data class SearchResult(val places: Places) : RecyclerViewItems() {
    override fun getItemViewType(): Int = VIEWTYPE_SEARCH_RESULT

    override fun onBindData(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as ViewHolder).bind(places)
    }

    override fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean =
        if (recyclerViewItems is SearchResult) places == recyclerViewItems.places else false

    class ViewHolder private constructor(private val binding: SuggestionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup) : this(
            SuggestionLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        fun bind(places: Places) {
            binding.name.text = places.name

            if (places.part_of.isNotEmpty())
                binding.partOf.text = places.part_of.last()

        }
    }
}