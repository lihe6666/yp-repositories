package com.yp2048.repositories.presentation.pickup

import androidx.lifecycle.ViewModel
import com.yp2048.repositories.data.api.PickUpResponse

class PickUpGuideViewModel : ViewModel() {

}

data class PickUpGuideUiState(
    var tools: PickUpResponse = PickUpResponse(
        code = 0,
        msg = "",
        rows = emptyList(),
        total = 0
    ),
    val selectedTools: List<Int> = emptyList(),
    val userMessage: String = "",
    val isLoading: Boolean = false,
)