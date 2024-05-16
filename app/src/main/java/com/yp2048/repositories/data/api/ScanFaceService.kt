package com.yp2048.repositories.data.api

import com.yp2048.repositories.data.remote.LoginResponse
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
}