package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import com.yp2048.repositories.data.api.RetrofitInstance
import com.yp2048.repositories.data.api.ToolResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PickUpGuideViewModel : ViewModel() {

}

data class PickUpGuideUiState(
    var tools: ToolResponse = ToolResponse(
        code = 0,
        msg = "",
        rows = emptyList(),
        total = 0
    ),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String = "",
    val isLoading: Boolean = false,
)