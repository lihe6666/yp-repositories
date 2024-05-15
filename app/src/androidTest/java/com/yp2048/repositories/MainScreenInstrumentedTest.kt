package com.yp2048.repositories

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainScreenInstrumentedTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun testMainScreen() = runTest {

        println("testMainScreen")
        assertEquals(4, 2 + 2)
    }

}