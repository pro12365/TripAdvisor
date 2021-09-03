package com.example.tripassistant.api

import com.example.tripassistant.data.models.Places

data class LocationApiResponse(
    val results: List<Places>
)