package com.example.tripassistant.ui.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.databinding.HeadingBinding


//Denotes a heading to be displayed as a recyclerview item
data class Heading(val text: String) : RecyclerViewItems() {
    override fun getItemViewType(): Int {
        return VIEWTYPE_HEADING
    }


    override fun onBindData(
        viewHolder: RecyclerView.ViewHolder,
    ) {
        (viewHolder as ViewHolder).bind(this)
    }


    override fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean =
        if (recyclerViewItems is Heading)text==recyclerViewItems.text else false


      class ViewHolder private constructor(private val binding: HeadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup) :
                this(
                    HeadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )


        fun bind(heading: Heading) {
            binding.text.text = heading.text
        }
    }

}
