package jp.ac.daido.kanainko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        return binding.root
    }
}