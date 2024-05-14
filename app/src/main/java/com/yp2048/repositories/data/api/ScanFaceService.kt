package com.yp2048.repositories.data.api

import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ScanFaceService {
    @Multipart
    @POST("/store/api/setFaceLogin")
    suspend fun setFaceLogin(@Part filePart: MultipartBody.Part): LoginResponse

    @GET("/store/api/relay/{state}")
    suspend fun setDoorState(@Path("state") state: String): LoginResponse
}


data class LoginResponse(
    val code: Int,
    val msg: String,
    val data: LoginData?
)

data class LoginData(
    val token: String
)