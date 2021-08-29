package com.example.tripassistant.api

import com.example.tripassistant.data.models.Hotels
import com.example.tripassistant.data.models.Places
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    companion object {
        const val BASE_URL = "https://www.triposo.com/api/20210615/"
        private const val ACCOUNT_ID = "GFHNZBC7"
        private const val ACCESS_TOKEN = "7x5ql1plvba65a2uvbi33qcpd0xk9x60"
    }

    @GET(
        "location.json?type=!national_park&fields=id,type,name,part_of&order_by=-trigram" +
                "&account=$ACCOUNT_ID&token=7$ACCESS_TOKEN"
    )
    suspend fun getSuggestion(@Query("annotate") query: String): Response<LocationApiResponse<Places>>

    @GET(
        "poi.json?tag_labels=hotels&order_by=-hotels_score&count=50&fields=id,name,coordinates,facebook_id,score,intro,images,properties,best_for,tag_labels,tags,booking_info,attribution,price_tier,structured_content,opening_hours,snippet,musement_venue_id,location_id,location_ids,tour_ids,intro_language_info,structured_content_language_info,snippet_language_info"
                + "&account=$ACCOUNT_ID&token=7$ACCESS_TOKEN"
    )
    suspend fun getHotelList(@Query("location_id") locationId: String): Response<LocationApiResponse<Hotels>>
}