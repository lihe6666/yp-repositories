package com.yp2048.repositories.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ScanFaceService
import com.yp2048.repositories.presentation.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class MainViewModel: ViewModel() {

    private val  apiService: ScanFaceService = RetrofitInstance.apiService

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun updateButtonState(state: Boolean = false) {
        _uiState.update {
            it.copy(isButtonState = false)
        }
    }

    fun requestScanFace(file: MultipartBody.Part) {
        // Loading
        _uiState.update {
            it.copy(isLoading = true, isButtonState = false)
        }

        viewModelScope.launch {
            // api call
            val response = apiService.setFaceLogin(file)
            delay(3000)

            if (response.code == 200) {
                TokenManager.setToken(response.data?.token)

                _uiState.update {
                    it.copy(isScanFace = true)
                }

                return@launch
            } else {
                _uiState.update {
                    it.copy(userMessage = response.msg)
                }
            }

            delay(3000)
            _uiState.update {
                it.copy(isLoading = false, isButtonState = true)
            }
        }
    }
}

data class MainUiState(
    val isScanFace: Boolean = false,
    val isButtonState: Boolean = true,
    val userMessage: String = "",
    val isLoading: Boolean = false
)