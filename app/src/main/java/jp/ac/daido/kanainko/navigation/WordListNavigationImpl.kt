package jp.ac.daido.kanainko.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import kuu.nagoya.featurewordlist.WordListNavigation

internal class WordListNavigationImpl(
    private val fragment: Fragment
) : WordListNavigation {
    override fun navigateToRecord() {
        fragment
            .findNavController()
            .navigate(R.id.action_chooseWordFragment_to_recordFragment)
    }
}