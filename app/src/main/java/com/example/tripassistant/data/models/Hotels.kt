package com.example.tripassistant.data.models

import androidx.recyclerview.widget.RecyclerView
import com.example.tripassistant.data.models.Places.Companion.TYPE_HOTEL
import com.example.tripassistant.ui.models.RecyclerViewItems

data class Hotels(
    val hotelId: String,
    val hotelName: String,
    val hotelCoordinates: Places.Coordinates,
    val hotelImages: List<Places.Image>
) : RecyclerViewItems() {

    override fun getItemViewType(): Int = TYPE_HOTEL
    override fun onBindData(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun areContentsTheSame(recyclerViewItems: RecyclerViewItems): Boolean =
        if (recyclerViewItems is Hotels) hotelName == recyclerViewItems.hotelName else false
}