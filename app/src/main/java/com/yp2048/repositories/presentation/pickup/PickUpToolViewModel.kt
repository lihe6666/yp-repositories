package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ToolData
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

            val response = toolsService.getStoreGoodsApiVo(id)

            if (response.code == 200) {
                _uiState.update {
                    it.copy(
                        tools = response.rows
                    )
                }
            }
        }
    }
}


data class PickUpUiState(
    val tools: List<ToolData> = listOf(ToolData()),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String = "",
    val isLoading: Boolean = false,
)