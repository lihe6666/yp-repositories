package com.yp2048.repositories.data.api

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ScanFaceService {
    @Multipart
    @POST("/store/api/setFaceLogin")
    suspend fun setFaceLogin(@Part filePart: MultipartBody.Part): LoginResponse
}


data class LoginResponse(
    val code: Int,
    val msg: String,
    val data: LoginData?
)

data class LoginData(
    val token: String
)