package kuu.nagoya.feature.result

import androidx.fragment.app.Fragment

interface ResultNavigation {
    fun navigateToHome(fragment: Fragment)
    fun navigateWordChooseScreen(fragment: Fragment)
}