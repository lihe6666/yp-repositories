package com.yp2048.repositories.data.repository

import com.yp2048.repositories.data.local.ScanFaceLocalDataSource
import com.yp2048.repositories.data.remote.LoginResponse
import com.yp2048.repositories.data.remote.ScanFaceRemoteDataSource
import okhttp3.MultipartBody

class ScanFaceRepository(
    private val scanFaceRemoteDataSource: ScanFaceRemoteDataSource,
    private val scanFaceLocalDataSource: ScanFaceLocalDataSource
) {

    suspend fun setFaceLogin(file: MultipartBody.Part): LoginResponse {
        // 优先从远程数据源获取数据
        return scanFaceRemoteDataSource.setFaceLogin(file)
    }

    suspend fun setDoorState(state: String): LoginResponse {
        // 优先从远程数据源获取数据
        return scanFaceRemoteDataSource.setDoorState(state)
    }
}