package com.yp2048.repositories.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yp2048.repositories.presentation.handback.HandBackGuideScreen
import com.yp2048.repositories.presentation.handback.HandBackScreen
import com.yp2048.repositories.presentation.main.MainScreen
import com.yp2048.repositories.presentation.main.MainViewModel
import com.yp2048.repositories.presentation.pickup.PickUpGuideScreen
import com.yp2048.repositories.presentation.pickup.PickUpScreen
import com.yp2048.repositories.presentation.pickup.PickUpViewModel

// 定义屏幕
enum class Screen(val route: String) {
    Home("Main"),
    Menu("Menu"),
    PickUp("PickUp"),
    PickUpGuide("PickUpGuide"),
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
        composable(Screen.PickUp.route) {
            PickUpScreen(
                navController = navController,
                viewModel = PickUpViewModel()
            )
        }
        composable(Screen.PickUpGuide.route) {
            PickUpGuideScreen(navController = navController)
        }
        composable(Screen.HandBack.route) {
            HandBackScreen(navController = navController)
        }
        composable(Screen.HandBackGuide.route) {
            HandBackGuideScreen(navController = navController)
        }
    }
}