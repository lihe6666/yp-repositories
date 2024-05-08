package com.yp2048.repositories.presentation

object TokenHolder {
    private var myString: String? = null

    fun getMyString(): String? {
        return myString
    }

    fun setMyString(myString: String?) {
        this.myString = myString
    }
}