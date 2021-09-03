package com.example.tripassistant.data.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripassistant.databinding.SectionItemBinding
import com.example.tripassistant.ui.models.RecyclerViewItems
import java.io.Serializable


class Places(
    val id: String = "",
    val name: String = "",
    val part_of: List<String> = listOf(),
    val coordinates: Coordinates = Coordinates(),
    val images: List<Image> = listOf(),
    val type: String = ""
) : RecyclerViewItems(), Serializable {

    companion object {
        const val TYPE_CITY = 0
        const val TYPE_COUNTRY = 1
        const val TYPE_HOTEL = 2
        const val TYPE_PLACE = 3
    }

    data class Coordinates(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
    )

    data class Image(val sizes: Sizes) {
        data class Sizes(val medium: Medium, val original: Original, val thumbnail: Thumbnail) {
            data class Medium(val url: String)
            data class Original(val url: String)
            data class Thumbnail(val url: String)
        }
    }

    class ViewHolder private constructor(private val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) :
                this(
                    SectionItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

        fun bind(places: Places) {
            if (places.images.isNotEmpty())
                Glide.with(binding.image).load(places.images[0].sizes.medium.url)
                    .into(binding.image)
            binding.title.text = places.name
        }
    }

    override fun getItemViewType(): Int = VIEWTYPE_PLACE

    override fun onBindData(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as ViewHolder).bind(this)
    }

    override fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean =
        if (recyclerViewItems is Places) id == recyclerViewItems.uid else false
}