package com.example.pertemuan_1hanyarunrun.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("dataList")
    object DataEntry : Screen ("dataEntry")
    object Add : Screen("add")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailReward : Screen("home/{rewardId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }

}
