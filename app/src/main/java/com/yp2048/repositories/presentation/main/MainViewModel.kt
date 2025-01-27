package com.yp2048.repositories.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.repository.ScanFaceRepository
import com.yp2048.repositories.presentation.MainUiState
import com.yp2048.repositories.presentation.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ScanFaceRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun resetUserMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    fun updateButtonState(state: Boolean = false) {
        _uiState.update {
            it.copy(isButtonState = state)
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
                val response = repository.setFaceLogin(file)

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
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(userMessage = "网络错误，请稍后重试")
                }
            } catch (e: HttpException) {
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