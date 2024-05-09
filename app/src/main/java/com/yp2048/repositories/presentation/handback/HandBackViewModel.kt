package com.yp2048.repositories.presentation.handback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.HandBackData
import com.yp2048.repositories.data.api.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HandBackViewModel: ViewModel() {

    private val handBackService = RetrofitInstance.handBackService

    private val _uiState = MutableStateFlow(HandBackUiState())
    val uiState: StateFlow<HandBackUiState> = _uiState.asStateFlow()

    fun fetchHandBackItems() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val response = handBackService.getStoreReceiveGoodLogApi()

            if (response.code == 200) {
                _uiState.update {
                    it.copy(data = response.rows)
                }
            }
            delay(3000)

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}

data class HandBackUiState(
    val data: List<HandBackData> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)