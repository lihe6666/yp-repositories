package com.yp2048.repositories.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yp2048.repositories.presentation.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.2.60:8080/"

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间
        .readTimeout(10, TimeUnit.SECONDS) // 设置读取超时时间
        .build()

    private val authClient: OkHttpClient = httpClient.newBuilder()
        .addInterceptor(AuthInterceptor()) // 添加认证拦截器
        .addInterceptor(ErrorInterceptor())
        .build()

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val apiService: ScanFaceService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
        retrofit.create(ScanFaceService::class.java)
    }

    val pickUpServer: PickUpServer by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(authClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        retrofit.create(PickUpServer::class.java)
    }

    val handBackService: HandBackService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(authClient)
            .build()

        retrofit.create(HandBackService::class.java)
    }
}

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // 从 TokenManager 获取令牌
        val token = TokenManager.getToken()

        // 构建包含 Authorization 头部的新请求
        val requestWithAuth = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(requestWithAuth)
    }
}

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (e: ConnectException) {
            throw LocalConnectException(e)
        }
    }
}

class LocalConnectException(cause: Throwable) : IOException("连接被拒绝", cause)