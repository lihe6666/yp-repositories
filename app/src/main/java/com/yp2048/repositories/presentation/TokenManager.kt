package com.yp2048.repositories.presentation

object TokenManager {
    private var token: String? = null

    fun getToken(): String? {
        return token
    }

    fun setToken(myString: String?) {
        this.token = myString
    }
}