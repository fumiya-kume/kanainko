package kuu.nagoya.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog.Builder
import androidx.fragment.app.Fragment
import kuu.nagoya.dashboard.R
import kuu.nagoya.dashboard.databinding.FragmentDashboardBinding
import kuu.nagoya.dashboard.viewentity.RecordViewEntity
import kuu.nagoya.navigation.DashboardNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DashboardFragment : Fragment() {

    private val dashboardNavigation: DashboardNavigation by inject(parameters = { parametersOf(this) })
    private val dashBoardViewModel: DashboardViewModel by viewModel()


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

        musicListAdapter.onMusicListItemClickListener =
            object : OnMusicListItemClickListener {
                override fun clicked(musicItem: RecordViewEntity) {
                    dashBoardViewModel.playRecordAudio(musicItem)
                }
            }

        dashBoardViewModel
            .recordListLiveData
            .observeForever {
                musicListAdapter.submitList(it)
                binding.fragmentDashboardEmptyRecordMessageTextView.visibility = if (it.isEmpty()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

        binding.fragmentDashboardMusicListRecyclerView.adapter = musicListAdapter
        binding.fragmentDashboardEditFloatingActionButton.setOnClickListener {
            dashboardNavigation.navigateAnalyzer()
        }
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard, menu)

        val resetMenu = menu.findItem(R.id.dashboard_menu_reset)
            .apply {
                this.setOnMenuItemClickListener {
                    val alertDialog = Builder(requireContext())
                        .setTitle("リセットしても大丈夫ですか？")
                        .setMessage("デバッグのために事前に設定されたものが表示されます")
                        .setNegativeButton("キャンセル") { _, _ ->
                        }
                        .setPositiveButton("OK") { _, _ ->
                            dashBoardViewModel.reset()
                        }
                    alertDialog.show()
                    true
                }
            }
        val removeAllMenu = menu.findItem(R.id.dashboard_menu_remove_all)
            .apply {
                this.setOnMenuItemClickListener {
                    val alertDialog = Builder(requireContext())
                        .setTitle("全てを削除しても大丈夫ですか？")
                        .setNegativeButton("キャンセル") { _, _ ->
                        }
                        .setPositiveButton("OK") { _, _ ->
                            dashBoardViewModel.removeAll()
                        }
                    alertDialog.show()
                    true
                }
            }

    }


}
