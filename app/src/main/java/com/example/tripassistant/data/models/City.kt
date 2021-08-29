package com.example.tripassistant.data.models

import java.io.Serializable

data class City(
    val cityId: String,
    val parent_id: String,
    val country_id: String,
    val cityName: String,
    val cityCoordinates: Coordinates,
    val part_of: List<String>,
    val snippet: String,
    val cityImages: List<Image>
) : Places(cityId, cityName, cityCoordinates, cityImages,TYPE_CITY),Serializable {
    override fun getItemViewType() = TYPE_CITY
}