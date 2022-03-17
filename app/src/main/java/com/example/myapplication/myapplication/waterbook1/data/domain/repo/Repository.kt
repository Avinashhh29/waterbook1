package com.example.myapplication.myapplication.waterbook1.data.domain.repo

import com.example.myapplication.myapplication.waterbook1.data.api.RetrofitInstance

class Repository {

    suspend fun getAllDetails(pincode:String) =
        RetrofitInstance.api.getAllDetails(pincode)
}