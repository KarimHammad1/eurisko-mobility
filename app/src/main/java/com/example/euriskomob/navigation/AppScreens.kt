package com.example.euriskomob.navigation

enum class AppScreens {
    SplashScreen,
    LoginScreen,
    MainScreen,
    TimerScreen,
    ListViewScreen,
    ListInfo;

    companion object{
        fun fromRoute(route:String?):AppScreens = when(route?.substringBefore("/")){
            SplashScreen.name->SplashScreen
            LoginScreen.name->LoginScreen
            MainScreen.name->MainScreen
            TimerScreen.name->TimerScreen
            ListViewScreen.name->ListViewScreen
            ListInfo.name->ListInfo
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}