package com.example.tripassistant.data.models

data class Hotels(
    val hotelId:String,
    val hotelName:String,
    val hotelCoordinates: Coordinates,
    val hotelImages:List<Image>
): Places(hotelId,hotelName,hotelCoordinates,hotelImages,TYPE_HOTEL) {

    override fun getItemViewType(): Int = TYPE_HOTEL
}