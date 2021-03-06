package com.example.tripassistant.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripassistant.data.models.Places
import com.example.tripassistant.ui.models.RecyclerViewItems
import com.example.tripassistant.databinding.SectionItemBinding

class ChildItemAdapter(private val items: List<RecyclerViewItems>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            SectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: RecyclerViewItems) {
            items as Places
            if (items.images.isNotEmpty())
                Glide.with(binding.image).load(items.images[0].sizes.medium.url).into(binding.image)
            binding.title.text = items.name
        }
    }
}