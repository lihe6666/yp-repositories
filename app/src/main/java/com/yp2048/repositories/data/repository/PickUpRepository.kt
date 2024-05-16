package com.yp2048.repositories.data.repository

import com.yp2048.repositories.data.api.PickUpResponse
import com.yp2048.repositories.data.api.WarehouseResponse
import com.yp2048.repositories.data.local.PickUpLocalDataSource
import com.yp2048.repositories.data.remote.PickUpRemoteDataSource
import com.yp2048.repositories.presentation.pickup.Device

class PickUpRepository(
    private val pickUpRemoteDataSource: PickUpRemoteDataSource = PickUpRemoteDataSource(),
    private val pickUpLocalDataSource: PickUpLocalDataSource = PickUpLocalDataSource(),
) {

    suspend fun getWarehouseInformation(): WarehouseResponse {
        return pickUpRemoteDataSource.getWarehouseInformation()
    }

    suspend fun getStoreGoodsApiVo(id: String): PickUpResponse {
        return pickUpRemoteDataSource.getStoreGoodsApiVo(id)
    }

    suspend fun setStoreReceiveGoodLog(body: MutableMap<String, MutableList<Device>>): PickUpResponse {
        return pickUpRemoteDataSource.setStoreReceiveGoodLog(body)
    }
}