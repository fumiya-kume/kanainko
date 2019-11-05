package kuu.nagoya.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.dashboard.databinding.FragmentDashboardBinding
import kuu.nagoya.navigation.DashboardNavigation
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DashboardFragment : Fragment() {

    val dashboardNavigation: DashboardNavigation by inject(parameters = { parametersOf(this) })

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
            dashboardNavigation.navigateAnalyzer()
        }
        return binding.root
    }
}
