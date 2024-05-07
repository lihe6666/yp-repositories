package com.yp2048.repositories.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ScanFaceService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class MainViewModel: ViewModel() {

    private val  apiService: ScanFaceService = RetrofitInstance.api

    val isLoading = MutableLiveData(false)

    fun requestScanFace(file: MultipartBody.Part) {
        // Add your code here
        isLoading.value = true
        viewModelScope.launch {

            val response = apiService.setFaceLogin(file)

            Log.d("MainViewModel", "requestScanFace: ${response.code}")

            delay(3000)
            isLoading.value = false
        }
    }
}