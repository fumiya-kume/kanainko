package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.navigation.AppNavigation

internal class AppNavigationImpl(
    private val fragment: Fragment
) : AppNavigation {
    override fun navigateToWordChoose() {
        fragment
            .findNavController()
            .navigate(R.id.action_mainFragment_to_chooseWordFragment)
    }

    override fun navigateToResultDemo() {
        fragment
            .findNavController()
            .navigate(R.id.action_mainFragment_to_resultDemoFragment)
    }

    override fun navigateToDashboard() {
        fragment
            .findNavController()
            .navigate(R.id.action_mainFragment_to_dashboardFragment)
    }
}