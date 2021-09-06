package com.example.tripassistant.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripassistant.api.LocationApi
import com.example.tripassistant.api.LocationApiResponse
import com.example.tripassistant.data.LocationRepository
import com.example.tripassistant.ui.models.Heading
import com.example.tripassistant.ui.models.RecyclerViewItems
import com.example.tripassistant.ui.models.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    val locationList = MutableLiveData<List<SearchResult>>()
    val networkRequestStatus = MutableLiveData(LocationApiResponse.STATUS_SUCCESS)

    fun getSuggestion(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRequestStatus.postValue(LocationApiResponse.STATUS_LOADING)
            val response = repository.getSuggestion(query)
            if (response.isSuccessful) {
                networkRequestStatus.postValue(LocationApiResponse.STATUS_SUCCESS)
                locationList.postValue(response.body()?.results?.map {place-> SearchResult(place) })
            } else {
                response.errorBody()?.let { Log.e("HttpError", it.string()) }
                networkRequestStatus.postValue(LocationApiResponse.STATUS_ERROR)
            }
        }
    }

    fun getDefaultData(): List<RecyclerViewItems> {
        return listOf(
            Heading("Popular Destinations")
        )
    }

}