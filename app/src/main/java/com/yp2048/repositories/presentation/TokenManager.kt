package com.yp2048.repositories.presentation

object TokenManager {
    private var token: String? = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiJzeXNfdXNlcjoxNzg3NzY3NDcxMDg2NjMyOTYyIiwicm5TdHIiOiJFeG03RzdyTk80bG1iT0RTamRxSFphaUd0RURQdjNscSIsInVzZXJJZCI6MTc4Nzc2NzQ3MTA4NjYzMjk2Mn0.KFA6MUlez2jX2m4RhvPYoLlxjuZhHx4_R4t741_UmCM"

    fun getToken(): String? {
        return token
    }

    fun setToken(myString: String?) {
        this.token = myString
    }
}