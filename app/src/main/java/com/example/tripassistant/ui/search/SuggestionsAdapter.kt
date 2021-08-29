package com.example.tripassistant.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.data.models.City
import com.example.tripassistant.databinding.HeadingBinding
import com.example.tripassistant.databinding.SuggestionLayoutBinding
import com.example.tripassistant.ui.models.Heading
import com.example.tripassistant.ui.models.RecyclerViewItems

class SuggestionsAdapter(val navController: NavController) :
    ListAdapter<RecyclerViewItems, RecyclerView.ViewHolder>(DiffCalculator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RecyclerViewItems.VIEWTYPE_PLACE)
            return LocationViewHolder(
                SuggestionLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else
            return HeadingViewHolder(
                HeadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (holder is HeadingViewHolder)
            holder.bind(currentItem as Heading)
        else if (holder is LocationViewHolder) {
            holder.bind(currentItem as City)
            holder.binding.root.setOnClickListener {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToHotel2(currentItem)
                navController.navigate(action)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).getItemViewType()
    }

    class LocationViewHolder(val binding: SuggestionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
//            binding.root.setOnClickListener {
//            }
        }

        fun bind(location: City) {
            binding.name.text = location.name

            if (location.part_of.isNotEmpty()) binding.partOf.text =
                location.part_of.last()

        }
    }

    class HeadingViewHolder(private val binding: HeadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(heading: Heading) {
            binding.text.text = heading.text
        }
    }

    class DiffCalculator : DiffUtil.ItemCallback<RecyclerViewItems>() {
        override fun areItemsTheSame(
            oldItem: RecyclerViewItems,
            newItem: RecyclerViewItems
        ): Boolean {
            return oldItem is City && newItem is City
        }

        override fun areContentsTheSame(
            oldItem: RecyclerViewItems,
            newItem: RecyclerViewItems
        ): Boolean {
            if (oldItem is City && newItem is City) {
                return oldItem.name.equals(
                    newItem.name,
                    true
                ) && oldItem.part_of.equals(newItem.part_of)
            } else if (oldItem is Heading && newItem is Heading)
                oldItem.text.equals(newItem.text, false)

            return false
        }
    }

}