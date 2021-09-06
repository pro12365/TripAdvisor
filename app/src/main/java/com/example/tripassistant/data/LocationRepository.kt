package com.example.tripassistant.data

import com.example.tripassistant.api.LocationApi
import com.example.tripassistant.api.LocationApiResponse
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApi: LocationApi
) {

    suspend fun getSuggestion(query: String): Response<LocationApiResponse> {
        return locationApi.getSuggestion("trigram:$query")
    }
    suspend fun getHotelList(locationId: String): Response<LocationApiResponse> {
        return locationApi.getHotelList(locationId)
    }
}