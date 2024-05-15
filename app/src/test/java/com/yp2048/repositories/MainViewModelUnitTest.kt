package com.yp2048.repositories

import com.yp2048.repositories.data.remote.LoginResponse
import com.yp2048.repositories.data.repository.ScanFaceRepository
import com.yp2048.repositories.presentation.main.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: ScanFaceRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MainViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

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
    fun testRequestScanFaceSuccess(): Unit = runTest {
        // Mock API response
        val mockResponse = LoginResponse(200, "操作完成", null)
        coEvery { repository.setFaceLogin(any()) } returns mockResponse

        // Call the method to be tested
        viewModel.requestScanFace(mockk())

        // Verify that the repository method was called
        coVerify { repository.setFaceLogin(any()) }

        // Verify UI state after successful API call
        assertEquals(true, viewModel.uiState.value.isScanFace)
        assertEquals("操作完成", viewModel.uiState.value.userMessage)
        assertEquals(true, viewModel.uiState.value.isLoading)
        assertEquals(false, viewModel.uiState.value.isButtonState)
    }
}