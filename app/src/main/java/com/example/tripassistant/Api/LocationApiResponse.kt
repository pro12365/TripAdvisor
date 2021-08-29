package com.example.tripassistant.api

data class LocationApiResponse<T>(
    val results: List<T>
) {
    companion object {
        const val STATUS_SUCCESSFUL = 0
        const val STATUS_LOADING = 1
        const val STATUS_ERROR = 2
    }
}