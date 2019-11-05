package jp.ac.daido.kanainko.result.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import jp.ac.daido.kanainko.databinding.FragmentResultBinding

internal class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        binding.fragmentResultNavigateDashboardButton
            .setOnClickListener {
                findNavController().navigate(R.id.action_resultFragment_to_dashboardFragment)
            }

        return binding.root
    }
}