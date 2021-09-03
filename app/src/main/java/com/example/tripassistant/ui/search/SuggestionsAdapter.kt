//package com.example.tripassistant.ui.search
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.tripassistant.data.models.City
//import com.example.tripassistant.databinding.HeadingBinding
//import com.example.tripassistant.databinding.SuggestionLayoutBinding
//import com.example.tripassistant.ui.models.Heading
//import com.example.tripassistant.ui.models.RecyclerViewItems
//
//class SuggestionsAdapter(val itemClickListener: ItemClickListener) :
//    ListAdapter<RecyclerViewItems, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (viewType == RecyclerViewItems.VIEWTYPE_PLACE)
//            return LocationViewHolder(
//                SuggestionLayoutBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            )
//        else
//            return HeadingViewHolder(
//                HeadingBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val currentItem = getItem(position)
//        if (holder is HeadingViewHolder)
//            holder.bind(currentItem as Heading)
//        else if (holder is LocationViewHolder) {
//            holder.bind(currentItem as City)
//        }
//    }
//
//
//    override fun getItemViewType(position: Int): Int {
//        return getItem(position).getItemViewType()
//    }
//
//    inner class LocationViewHolder(private val binding: SuggestionLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        init {
//            itemView.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION)
//                    itemClickListener.onItemClick(position)
//            }
//        }
//
//        fun bind(location: City) {
//            binding.name.text = location.name
//
//            if (location.part_of.isNotEmpty()) binding.partOf.text =
//                location.part_of.last()
//
//        }
//    }
//
//    class HeadingViewHolder(private val binding: HeadingBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(heading: Heading) {
//            binding.text.text = heading.text
//        }
//    }
//
//    companion object {
//        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItems>() {
//            override fun areItemsTheSame(
//                oldItem: RecyclerViewItems,
//                newItem: RecyclerViewItems
//            ) = oldItem.getItemViewType() == newItem.getItemViewType()
//
//            override fun areContentsTheSame(
//                oldItem: RecyclerViewItems,
//                newItem: RecyclerViewItems
//            ) = false
//
//        }
//    }
//
//    interface ItemClickListener {
//        fun onItemClick(position: Int)
//    }
//}