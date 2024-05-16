package com.yp2048.repositories.data.remote

import com.yp2048.repositories.data.api.PickUpResponse
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.WarehouseResponse
import com.yp2048.repositories.presentation.pickup.Device

class PickUpRemoteDataSource {

    suspend fun getWarehouseInformation(): WarehouseResponse {
        return RetrofitInstance.pickUpServer.getWarehouseInformation()
    }

    suspend fun getStoreGoodsApiVo(id: String): PickUpResponse {
        return RetrofitInstance.pickUpServer.getStoreGoodsApiVo(id)
    }

    suspend fun setStoreReceiveGoodLog(body: MutableMap<String, MutableList<Device>>): PickUpResponse {
        return RetrofitInstance.pickUpServer.setStoreReceiveGoodLog(body)
    }
}