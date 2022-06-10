package com.example.euriskomob.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.euriskomob.component.TopBar
import com.example.euriskomob.model.UserResponse
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ListInfo(navController: NavController,listId:String?){
    TopBar(navController = navController, title = "ListInfo")


   Text(text = listId.toString(), modifier = Modifier.padding(40.dp))

}

