package com.example.tripassistant.api

import com.example.tripassistant.data.models.Places

data class LocationApiResponse(
    val results: List<Places>
){
    companion object{
        const val STATUS_LOADING = 0
        const val STATUS_SUCCESS = 1
        const val STATUS_ERROR = 2
    }
}