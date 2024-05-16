package com.yp2048.repositories.data.remote

import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.data.api.HandBackResponse
import com.yp2048.repositories.data.api.RetrofitInstance

class HandBackRemoteDataSource {

    suspend fun getStoreReceiveGoodLogApi(): HandBackResponse {
        return RetrofitInstance.handBackService.getStoreReceiveGoodLogApi()
    }

    suspend fun getStoreReceiveGoodLog(body: MutableMap<String, MutableList<HandBackBody>>): HandBackResponse {
        return RetrofitInstance.handBackService.getStoreReceiveGoodLog(body)
    }

    suspend fun setDoorState(typeId: String): LoginResponse {
        return RetrofitInstance.handBackService.setDoorState(typeId)
    }
}