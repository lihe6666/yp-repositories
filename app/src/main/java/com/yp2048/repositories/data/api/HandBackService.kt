package com.yp2048.repositories.data.api

import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface HandBackService {

    @GET("/store/api/getStoreReceiveGoodLogApi")
    suspend fun getStoreReceiveGoodLogApi(): HandBackResponse

    @Headers("Content-Type: application/json")
    @POST("/store/api/getStoreReceiveGoodLog")
    suspend fun getStoreReceiveGoodLog(@Body body: MutableMap<String, List<HandBackBody>>): HandBackResponse
}


data class HandBackData(
    val id: String,
    val fullName: String,
    val storeInfo: String,
    val storageId: String,
    val deviceNumber: Int,
    val imageUrl: String? = null
)


@JsonClass(generateAdapter = true)
data class HandBackBody(
    val id: String,
    var deviceNumber: Int,
    val giveBackId: String,
)