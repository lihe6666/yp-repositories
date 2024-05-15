package com.yp2048.repositories.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.repository.ScanFaceRepository
import com.yp2048.repositories.presentation.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.IOException

class MainViewModel(private val scanFaceRepository: ScanFaceRepository): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun resetUserMessage() {
        _uiState.update {
            it.copy(userMessage = "")
        }
    }

    fun updateButtonState(state: Boolean = false) {
        _uiState.update {
            it.copy(isButtonState = state)
        }
    }

    fun updateDoorState()  {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            // try catch
            val response = scanFaceRepository.setDoorState("1")

            if (response.code == 200) {
                _uiState.update {
                    it.copy(userMessage = response.msg)
                }
            } else {
                _uiState.update {
                    it.copy(userMessage = response.msg)
                }
            }

            delay(3000)
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun requestScanFace(file: MultipartBody.Part) {
        // Loading
        _uiState.update {
            it.copy(isLoading = true, isButtonState = false)
        }

        viewModelScope.launch {
            // api call
            try {
                val response = scanFaceRepository.setFaceLogin(file)

                if (response.code == 200) {
                    TokenManager.setToken(response.data?.token)

                    _uiState.update {
                        it.copy(isScanFace = true, userMessage = response.msg)
                    }

                } else {
                    _uiState.update {
                        it.copy(userMessage = response.msg)
                    }
                }

                delay(3000)
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(userMessage = "网络错误，请稍后重试")
                }
            }

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