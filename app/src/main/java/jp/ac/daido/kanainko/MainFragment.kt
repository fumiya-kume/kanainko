package jp.ac.daido.kanainko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prevent.voice_data.domain.AssetsInitUsecase
import jp.ac.daido.kanainko.databinding.FragmentMainBinding
import kuu.nagoya.navigation.AppNavigation
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment() {

    val appNavigation: AppNavigation by inject(parameters = { parametersOf(this) })
    private val assetsInitUsecase: AssetsInitUsecase by inject()

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

        assetsInitUsecase.execute()

        binding.fragmentMainNavigateDashboardFragmentButton.setOnClickListener {
            appNavigation.navigateToDashboard()
        }
        binding.fragmentMainNavigateWordChooseButton.setOnClickListener {
            appNavigation.navigateToWordChoose()
        }

        binding.fragmentMainNavigateDebugButton
            .setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_debugActivity)
            }

        return binding.root
    }
}
