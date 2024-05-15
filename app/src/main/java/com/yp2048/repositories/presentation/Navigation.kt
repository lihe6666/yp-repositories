package com.yp2048.repositories.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yp2048.repositories.data.repository.ScanFaceRepository
import com.yp2048.repositories.presentation.handback.HandBackGuideScreen
import com.yp2048.repositories.presentation.handback.HandBackScreen
import com.yp2048.repositories.presentation.main.MainScreen
import com.yp2048.repositories.presentation.main.MainViewModel
import com.yp2048.repositories.presentation.pickup.PickUpBoardScreen
import com.yp2048.repositories.presentation.pickup.PickUpGuideScreen
import com.yp2048.repositories.presentation.pickup.PickUpToolScreen

// 定义屏幕
enum class Screen(val route: String) {
    Home("Main"),
    Menu("Menu"),
    PickUpBoard("PickUpBoard"),
    PickUpTool("PickUpTool/{id}"),
    PickUpGuide("PickUpGuide/{id}"),
    HandBack("HandBack"),
    HandBackGuide("HandBackGuide")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    // 导航主机
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MainScreen(
                navController = navController,
                mainViewModel = MainViewModel()
            )
        }
        composable(Screen.Menu.route) {
            MenuScreen(
                navController = navController
            )
        }
        composable(Screen.PickUpBoard.route) {
            PickUpBoardScreen(
                navController = navController
            )
        }
        composable(Screen.PickUpTool.route, arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )) {
            PickUpToolScreen(
                navController = navController,
                id = it.arguments?.getString("id")
            )
        }
        composable(Screen.PickUpGuide.route, arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = true
                defaultValue = "0"
            }
        )) {
            PickUpGuideScreen(
                navController = navController,
                id = it.arguments?.getString("id") ?: "0"
            )
        }
        composable(Screen.HandBack.route) {
            HandBackScreen(navController = navController)
        }
        composable(Screen.HandBackGuide.route) {
            HandBackGuideScreen(navController = navController)
        }
    }
}