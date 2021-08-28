package com.example.tripassistant.ui.hotels

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripassistant.R
import com.example.tripassistant.databinding.HotelBinding
import com.example.tripassistant.data.Location


class HotelAdapter(private val hotels: List<Location>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(HotelBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(hotels[position])
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    class ViewHolder(private val binding: HotelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.name.text = location.name

            if (location.images.isNotEmpty())
                Glide.with(binding.image).load(location.images[0].getUrl())
                    .into(binding.image)
            else {
                binding.image.setImageResource(R.drawable.error_placeholder)
                binding.image.scaleType = ImageView.ScaleType.FIT_XY
            }
        }
    }

}