package com.yp2048.repositories.presentation.handback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.HandBackBody
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.data.repository.HandBackRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class HandBackViewModel(
    private val handBackRepository: HandBackRepository = HandBackRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HandBackUiState())
    val uiState: StateFlow<HandBackUiState> = _uiState.asStateFlow()

    fun resetUserMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    fun updateDoorState()  {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            // try catch
            val response = handBackRepository.setDoorState("1")
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

    fun fetchHandBackItems() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            try {
                val response = handBackRepository.getStoreReceiveGoodLogApi()

                if (response.code == 200) {
                    _uiState.update {
                        it.copy(data = response.rows)
                    }
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(userMessage = "网络错误，请稍后重试")
                }
            }

            delay(3000)

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun fetchHandBackPackages(body: MutableMap<String, MutableList<HandBackBody>>) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            delay(3000)
            try {
                val response = handBackRepository.getStoreReceiveGoodLog(body)

                _uiState.update {
                    it.copy(userMessage = response.msg)
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(userMessage = "网络错误，请稍后重试")
                }
            }

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}

data class HandBackUiState(
    val data: List<HandBackData> = emptyList(),
    val userMessage: String? = "",
    val isLoading: Boolean = false,
    val packages: MutableMap<String, List<HandBackBody>> = mutableMapOf()
)