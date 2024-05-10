package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonClass
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.PickUpData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PickUpToolViewModel: ViewModel() {

    private val toolsService = RetrofitInstance.toolsService

    // StateFlow
    private val _uiState = MutableStateFlow(PickUpUiState())
    val uiState: StateFlow<PickUpUiState> = _uiState.asStateFlow()

    fun updateTools(id: String) {

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val response = toolsService.getStoreGoodsApiVo(id)
            if (response.code == 200) {
                _uiState.update {
                    it.copy(
                        tools = response.rows
                    )
                }
            }
            delay(3000)

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun updatePickUpPackages(body: MutableMap<String, List<Device>>) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            delay(3000)
            val response = toolsService.setStoreReceiveGoodLog(body)

            _uiState.update {
                it.copy(userMessage = response.msg)
            }

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}


data class PickUpUiState(
    val tools: List<PickUpData> = listOf(PickUpData()),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String? = "",
    val isLoading: Boolean = false,
)

@JsonClass(generateAdapter = true)
data class Device(val id: String, var deviceNumber: String)