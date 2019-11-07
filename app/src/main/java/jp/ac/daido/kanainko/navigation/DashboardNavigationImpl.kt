package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.navigation.DashboardNavigation

internal class DashboardNavigationImpl(
    private val fragment: Fragment
) : DashboardNavigation {
    override fun navigateAnalyzer() {
        fragment
            .findNavController()
            .navigate(R.id.action_dashboardFragment_to_recordFragment)
    }
}