package com.example.euriskomob.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.euriskomob.component.TopBar

@Composable
fun TimerScreen(navController: NavController){
    TopBar(navController = navController, title ="TimerScreen")

}