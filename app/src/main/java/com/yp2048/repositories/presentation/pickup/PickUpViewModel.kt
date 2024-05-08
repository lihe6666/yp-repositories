package com.yp2048.repositories.presentation.pickup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ToolResponse
import kotlinx.coroutines.launch

class PickUpViewModel(position: String = "1"): ViewModel() {

    private val toolsService = RetrofitInstance.toolsService

    private val _pickUpUiState = MutableLiveData(PickUpUiState())
    var pickUpUiState: LiveData<PickUpUiState> = _pickUpUiState

    private val _tools = MutableLiveData(ToolResponse())

    var tools: LiveData<ToolResponse> = _tools

    fun fetchPositionTools(id: String) {

        viewModelScope.launch {
            val response = toolsService.getStoreGoodsApiVo(id)

            if (response.code == 200) {
                Log.d("getStoreGoodsApiVo", "$response")
                _tools.value = response
            }
            // TODO else

        }
    }
}


data class PickUpUiState(
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String = "",
    val isLoading: Boolean = false,
)