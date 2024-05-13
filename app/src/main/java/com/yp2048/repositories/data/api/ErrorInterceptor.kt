package com.yp2048.repositories.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (e: ConnectException) {
            throw MyConnectException(e)
        }
    }
}

class MyConnectException(cause: Throwable) : IOException("连接被拒绝", cause)