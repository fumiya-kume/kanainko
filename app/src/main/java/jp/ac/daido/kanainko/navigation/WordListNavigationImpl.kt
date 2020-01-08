package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.featurewordlist.WordListNavigation
import kuu.nagoya.util.navigateWithSingletop

internal class WordListNavigationImpl(
    private val fragment: Fragment
) : WordListNavigation {
    override fun navigateToRecord() {
        val navigationOption = NavOptions.Builder().setLaunchSingleTop(true).build()
        fragment
            .findNavController()
            .navigateWithSingletop(R.id.action_chooseWordFragment_to_recordFragment)
    }
}