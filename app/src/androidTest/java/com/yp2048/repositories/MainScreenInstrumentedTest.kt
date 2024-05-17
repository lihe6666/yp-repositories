package com.yp2048.repositories

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainScreenInstrumentedTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun testMainScreen() {

        println("testMainScreen")
        assertEquals(4, 2 + 2)
    }

}