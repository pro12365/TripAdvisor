package com.example.tripassistant.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripassistant.api.LocationApiResponse
import com.example.tripassistant.data.LocationRepository
import com.example.tripassistant.data.models.Places
import com.example.tripassistant.ui.models.Heading
import com.example.tripassistant.ui.models.RecyclerViewItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    val locationList = MutableLiveData<List<Places>>()
    val networkRequestStatus = MutableLiveData(LocationApiResponse.STATUS_SUCCESSFUL)

    fun getSuggestion(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRequestStatus.postValue(LocationApiResponse.STATUS_LOADING)
            val response = repository.getSuggestion(query)
            if (response.isSuccessful)
                networkRequestStatus.postValue(LocationApiResponse.STATUS_SUCCESSFUL)
            else
                networkRequestStatus.postValue(LocationApiResponse.STATUS_ERROR)
        }
    }

    fun getDefaultData(): List<RecyclerViewItems> {
        return listOf(
            Heading("Popular Destinations")
        )
    }

}