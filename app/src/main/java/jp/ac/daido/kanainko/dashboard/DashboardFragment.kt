package jp.ac.daido.kanainko.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import jp.ac.daido.kanainko.databinding.FragmentDashboardBinding

internal class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        val musicListAdapter = DashboardMusicListAdapter(requireContext())

        val dummyData = mutableListOf<MusicListViewEntity>().apply {
            for (i in 1..10) {
                this.add(
                    MusicListViewEntity(
                        i,
                        "Title $i"
                    )
                )
            }
        }
        musicListAdapter.submitList(dummyData)
        binding.fragmentDashboardMusicListRecyclerView.adapter = musicListAdapter
        binding.fragmentDashboardEditFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_recordFragment)
        }
        return binding.root
    }
}