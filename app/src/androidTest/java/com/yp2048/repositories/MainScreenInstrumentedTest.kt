package com.yp2048.repositories

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.yp2048.repositories.presentation.main.MainScreen
import com.yp2048.repositories.ui.theme.RepositoriesTheme
import org.junit.Rule
import org.junit.Test

class MainScreenInstrumentedTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun test_MainScreen_takePicture_navigatesToMenu() {
        composeTestRule.setContent {
            RepositoriesTheme(dynamicColor = false) {
                MainScreen(
                    navController = rememberNavController(),
                )
            }
        }

        val context: Context = ApplicationProvider.getApplicationContext()
        val resource: Resources = context.resources

        composeTestRule.onNodeWithText(resource.getString(R.string.scan_face))
            .assertIsEnabled()
        composeTestRule.onNodeWithText(resource.getString(R.string.scan_face))
            .assertHasClickAction()

        composeTestRule.onNodeWithText(resource.getString(R.string.scan_face))
            .performClick()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.scan_face))
            .assertIsNotEnabled()

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }

}