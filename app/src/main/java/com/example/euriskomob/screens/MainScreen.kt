package com.example.euriskomob.screens


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.euriskomob.component.TopBar


@Composable
fun MainScreen(navController: NavController){
    TopBar(navController = navController, title = "MainScreen")
}
