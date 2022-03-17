package com.example.myapplication.myapplication.waterbook1.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.myapplication.waterbook1.commons.Resource
import com.example.myapplication.myapplication.waterbook1.data.api.PincodesItem
import com.example.myapplication.myapplication.waterbook1.data.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PincodesViewModel (
    private val repository: Repository
    ) :ViewModel(){

    val pincodeDetails : MutableLiveData<Resource<List<String>>> = MutableLiveData()

    init {
        getAllDetails("600118")
    }
    fun getAllDetails(pincode:String) = viewModelScope.launch {
        pincodeDetails.postValue(Resource.Loading())
        val response = repository.getAllDetails(pincode)
        pincodeDetails.postValue(handlePincodeDetails(response))
    }

    private fun handlePincodeDetails(response: Response<List<PincodesItem>>): Resource<List<String>>? {
        var name : ArrayList<String> = ArrayList()
        if (response.isSuccessful) {
            response.body()?.let { pincodeItems->
                for (item in pincodeItems) {
                    name.add(item.Name)
                }

            }
        }
        return Resource.Error(response.message())
    }
}