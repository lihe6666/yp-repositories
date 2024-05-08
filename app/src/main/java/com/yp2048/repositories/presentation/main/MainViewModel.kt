package com.yp2048.repositories.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ScanFaceService
import com.yp2048.repositories.presentation.TokenHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class MainViewModel: ViewModel() {

    private val  apiService: ScanFaceService = RetrofitInstance.apiService

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _mainUiState = MutableLiveData(MainUiState(
        isScanFace = false,
        userMessage = "",
    ))

    val mainUiState: LiveData<MainUiState> = _mainUiState

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean>
        get() = _isButtonEnabled

    fun enableButtonAfterDelay() {
        viewModelScope.launch {
            delay(3000) // 3 秒延迟
            _isButtonEnabled.value = true // 使按钮可点击
        }
    }

    fun requestScanFace(file: MultipartBody.Part) {
        // Loading
        _isLoading.value = true
        _isButtonEnabled.value = false
        /*
        viewModelScope.launch {
            // api call
            val response = apiService.setFaceLogin(file)
            delay(1000)

            if (response.code == 200) {
                _mainUiState.value?.isScanFace = true
                TokenHolder.setMyString(response.data?.token)
            } else {
                _mainUiState.value?.userMessage = response.msg
            }

            delay(1000)
            _isLoading.value = false
            _isButtonEnabled.value = true
        }*/
        _mainUiState.value?.isScanFace = true
        _isLoading.value = false
        _isButtonEnabled.value = true
    }
}

data class MainUiState(
    var isScanFace: Boolean = false,
    var userMessage: String = "",
)