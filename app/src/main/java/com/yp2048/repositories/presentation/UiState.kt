package com.yp2048.repositories.presentation

data class MainUiState(
    val isScanFace: Boolean? = null,
    val isButtonState: Boolean = true,
    val userMessage: String? = null,
    val isLoading: Boolean? = null
)