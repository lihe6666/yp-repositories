package com.yp2048.repositories.data.api

import retrofit2.http.GET

interface HandBackService {

    @GET("/store/api/getStoreReceiveGoodLogApi")
    suspend fun getStoreReceiveGoodLogApi(): HandBackResponse
}


data class HandBackData(
    val id: String,
    val fullName: String,
    val storeInfo: String,
    val storageId: Int,
    val deviceNumber: Int,
    val imageUrl: String? = null
)