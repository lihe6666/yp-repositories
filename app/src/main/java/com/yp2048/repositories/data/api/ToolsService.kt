package com.yp2048.repositories.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ToolsService {

    @GET("/store/api/getStoreGoodsApiVo/{id}")
    suspend fun getStoreGoodsApiVo(@Path("id") id: String): ToolResponse
}

data class ToolResponse(
    val code: Int = 0,
    val msg: String = "",
    val rows: List<ToolData> = listOf(),
    val total: Int = 0
)

data class ToolData(
    val id: String = "",
    val fullName: String = "",
    val quantityInStock: String = "",
    val imageUrl: String? = null,
    val storageRack: Int = 0
)