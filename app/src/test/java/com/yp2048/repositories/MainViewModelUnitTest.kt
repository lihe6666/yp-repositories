package com.yp2048.repositories

import com.yp2048.repositories.data.repository.ScanFaceRepository
import com.yp2048.repositories.presentation.main.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: ScanFaceRepository

    @Test
    fun testMainUiState() {
        var currentUiState = viewModel.uiState.value
        currentUiState = currentUiState.copy(
            isScanFace = true,
            isButtonState = false,
            userMessage = "Hello World"
        )

        assertEquals(false, currentUiState.isButtonState)
        assertEquals(true, currentUiState.isScanFace)
        assertEquals("Hello World", currentUiState.userMessage)

        viewModel.resetUserMessage()
        assertEquals("", viewModel.uiState.value.userMessage)
    }

    @Test
    fun testRequestScanFaceSuccess() {
        // Mock API response
        // Call the method to be tested
        // Verify that the repository method was called

        // Verify UI state after successful API call
    }
}