package com.yp2048.repositories.data.remote

import com.yp2048.repositories.data.api.RetrofitInstance
import okhttp3.MultipartBody

class ScanFaceRemoteDataSource {
    suspend fun setFaceLogin(file: MultipartBody.Part): LoginResponse {
        return RetrofitInstance.apiService.setFaceLogin(file)
    }

    suspend fun setDoorState(state: String): LoginResponse {
        return RetrofitInstance.apiService.setDoorState(state)
    }
}

data class LoginResponse(
    val code: Int,
    val msg: String,
    val data: LoginData?
)

data class LoginData(
    val token: String
)