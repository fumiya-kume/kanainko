package kuu.nagoya.util

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateWithSingletop(resId: Int) {
    val navOptions =
        NavOptions
            .Builder()
            .setLaunchSingleTop(true)
            .build()
    this.navigate(resId, null, navOptions)
}