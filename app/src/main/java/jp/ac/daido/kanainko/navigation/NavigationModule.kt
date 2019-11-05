package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import kuu.nagoya.navigation.AppNavigation
import org.koin.dsl.module

val navigationModule = module {
    factory { (fragment: Fragment) -> AppNavigationImpl(fragment) as AppNavigation }
}