package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.WarehouseData
import com.yp2048.repositories.data.repository.PickUpRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.ConnectException

class PickUpGuideViewModel(
    private val pickUpRepository: PickUpRepository = PickUpRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(PickUpGuideUiState())
    val uiState: StateFlow<PickUpGuideUiState> = _uiState.asStateFlow()

    fun resetUserMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    fun fetchWarehouseInformation() {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            delay(3000)

            try {
                val response = pickUpRepository.getWarehouseInformation()

                if (response.code == 200) {
                    _uiState.update {
                        it.copy(data = response.data)
                    }
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(userMessage = "网络错误，请稍后重试")
                }
            } catch (e: ConnectException) {
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

data class PickUpGuideUiState(
    var data: WarehouseData = WarehouseData(
        storeWarehouseBitmapId = "",
        storeWarehouseBitmapName = "",
        storeWarehouseBitmapLong = 0,
        storeWarehouseBitmapWide = 0,
        storeWarehouseBitmapWidth = 0,
        storeWarehouseBitmapLength = 0,
        list = listOf(),
        storageRackList = listOf()
    ),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String? = null,
    val isLoading: Boolean = false,
)