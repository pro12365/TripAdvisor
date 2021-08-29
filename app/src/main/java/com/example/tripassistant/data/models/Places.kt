package com.example.tripassistant.data.models

import com.example.tripassistant.ui.models.RecyclerViewItems
import java.io.Serializable

//Base class for all types of locations
abstract class Places(
    val id: String,
    val name: String,
    val coordinates: Coordinates,
    val images: List<Image>,
    val type:Int
) : RecyclerViewItems, Serializable {

    companion object {
        const val TYPE_CITY = 0
        const val TYPE_COUNTRY = 1
        const val TYPE_HOTEL = 2
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
}