package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.navigation.RecordNavigation
import kuu.nagoya.util.navigateWithSingletop

internal class RecordNavigationImpl(
    private val fragment: Fragment
) : RecordNavigation {
    override fun navigateToAnalyzer() {
        fragment
            .findNavController()
            .navigateWithSingletop(R.id.action_recordFragment_to_resultFragment)
    }
}