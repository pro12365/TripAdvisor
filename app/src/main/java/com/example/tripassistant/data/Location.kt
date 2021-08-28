package com.example.tripassistant.data

import com.example.tripassistant.utils.ViewTypes
import java.io.Serializable

data class Location(
    val id: String = "",
    val parent_id: String = "",
    val coordinates: Coordinates = Coordinates(),
    val country_id: String = "",
    val type: String = "",
    val intro: String = "",
    val name: String = "",
    val part_of: List<String> = listOf(),
    val snippet: String = "",
    val images: List<Image> = listOf()
) : RecyclerViewItems, Serializable {
    override fun getItemViewType(): Int {
        return ViewTypes.LOCATION
    }
}
