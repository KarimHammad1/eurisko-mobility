package com.example.euriskomob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.euriskomob.screens.*


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name){
        composable(AppScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(AppScreens.LoginScreen.name) {
            AppLoginScreen(navController = navController)
        }
        composable(AppScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }
        composable(AppScreens.ListViewScreen.name) {
            ListViewScreen(navController = navController)
        }
        composable(AppScreens.TimerScreen.name){
            TimerScreen(navController = navController)
        }
        composable(AppScreens.ListInfo.name+"/{details}",
            arguments = listOf(navArgument(name = "details") {type = NavType.StringType})) {
                backStackEntry ->

            ListInfo(navController = navController,
                backStackEntry.arguments?.getString("details"))
        }
    }



}