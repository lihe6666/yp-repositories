package com.yp2048.repositories.data.api

import com.yp2048.repositories.presentation.pickup.Device
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PickUpServer {

    @GET("/store/api/getWarehouseInformation")
    suspend fun getWarehouseInformation(): WarehouseResponse

    @GET("/store/api/getStoreGoodsApiVo/{id}")
    suspend fun getStoreGoodsApiVo(@Path("id") id: String): PickUpResponse

    @POST("/store/api/setStoreReceiveGoodLog")
    suspend fun setStoreReceiveGoodLog(@Body body: MutableMap<String, MutableList<Device>>): PickUpResponse
}

data class PickUpData(
    val id: String = "",
    val fullName: String = "",
    val quantityInStock: String = "",
    val imageUrl: String? = null,
    val storageRack: Int = 0
)