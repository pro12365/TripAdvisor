package com.example.tripassistant.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.databinding.ExploreRowBinding
import com.example.tripassistant.data.Section

class ExploreAdapter(private val section: List<Section>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ExploreRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(section[position])
    }

    override fun getItemCount(): Int {
        return section.size
    }


    class ViewHolder(private val binding: ExploreRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

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