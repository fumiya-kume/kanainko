package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.feature.result.ResultNavigation

internal class ResultNavigationImpl : ResultNavigation {
    override fun navigateToHome(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_resultFragment_to_dashboardFragment)
    }

    override fun navigateWordChooseScreen(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_resultFragment_to_chooseWordFragment)
    }
}