package com.yp2048.repositories.data.repository

import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.data.api.HandBackResponse
import com.yp2048.repositories.data.local.HandBackLocalDataSource
import com.yp2048.repositories.data.remote.HandBackRemoteDataSource
import com.yp2048.repositories.data.remote.LoginResponse

class HandBackRepository(
    private val handBackRemoteDataSource: HandBackRemoteDataSource = HandBackRemoteDataSource(),
    private val handBackLocalDataSource: HandBackLocalDataSource = HandBackLocalDataSource(),
) {

    suspend fun getStoreReceiveGoodLogApi(): HandBackResponse {
        return handBackRemoteDataSource.getStoreReceiveGoodLogApi()
    }

    suspend fun getStoreReceiveGoodLog(body: MutableMap<String, MutableList<HandBackBody>>): HandBackResponse {
        return handBackRemoteDataSource.getStoreReceiveGoodLog(body)
    }

    suspend fun setDoorState(typeId: String): LoginResponse {
        return handBackRemoteDataSource.setDoorState(typeId)
    }
}