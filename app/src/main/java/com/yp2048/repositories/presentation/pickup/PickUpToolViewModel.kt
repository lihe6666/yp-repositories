package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonClass
import com.yp2048.repositories.data.api.PickUpData
import com.yp2048.repositories.data.repository.PickUpRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class PickUpToolViewModel(
    private val pickUpRepository: PickUpRepository = PickUpRepository()
): ViewModel() {

    // StateFlow
    private val _uiState = MutableStateFlow(PickUpUiState())
    val uiState: StateFlow<PickUpUiState> = _uiState.asStateFlow()

    fun resetUserMessage() {
        _uiState.update {
            it.copy(userMessage = "")
        }
    }

    fun updateTools(id: String) {

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val response = pickUpRepository.getStoreGoodsApiVo(id)
            if (response.code == 200) {
                _uiState.update {
                    it.copy(
                        data = response.rows
                    )
                }
            }
            delay(3000)

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun updatePickUpPackages(body: MutableMap<String, MutableList<Device>>) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            try {
                val response = pickUpRepository.setStoreReceiveGoodLog(body)

                if (response.code == 200) {
                    _uiState.update {
                        it.copy(isRoadMap = true)
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
}


data class PickUpUiState(
    val data: List<PickUpData> = listOf(PickUpData()),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String? = "",
    val isLoading: Boolean = false,
    val isRoadMap: Boolean = false
)

@JsonClass(generateAdapter = true)
data class Device(val id: String, var deviceNumber: String)