package com.example.myapplication.myapplication.waterbook1.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PincodeAPI {

    @GET("{Pincode}")
    suspend fun getAllDetails(@Path("Pincode")pincode:String):Response<List<PincodesItem>>
}