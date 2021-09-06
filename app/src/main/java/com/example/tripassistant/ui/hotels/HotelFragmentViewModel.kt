package com.example.tripassistant.ui.hotels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripassistant.api.LocationApiResponse
import com.example.tripassistant.data.LocationRepository
import com.example.tripassistant.data.models.Hotels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelFragmentViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    val hotelList = MutableLiveData<List<Hotels>>()
    val networkRequestStatus = MutableLiveData(LocationApiResponse.STATUS_SUCCESS)

    fun getHotelList(locationId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getHotelList(locationId).body()?.results
            hotelList.postValue(result as List<Hotels>)

        }
    }

}