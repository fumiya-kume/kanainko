package jp.ac.daido.kanainko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.databinding.FragmentMainBinding

internal class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(
            layoutInflater,
            container,
            false
        )

        binding.fragmentMainNavigateDashboardFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dashboardFragment)
        }
        binding.fragmentMainNavigateWordChooseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_chooseWordFragment)
        }

        return binding.root
    }
}
