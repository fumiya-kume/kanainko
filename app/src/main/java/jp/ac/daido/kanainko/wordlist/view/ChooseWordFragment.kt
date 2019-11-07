package jp.ac.daido.kanainko.wordlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.databinding.FragmentChooseWordBinding
import jp.ac.daido.kanainko.entity.RecordWordEntity
import jp.ac.daido.kanainko.wordlist.view.dialog.ChooseWordDialog
import jp.ac.daido.kanainko.wordlist.view.dialog.OnWordChooseDoneListener
import jp.ac.daido.kanainko.wordlist.viewentity.WordGroupViewEntity
import jp.ac.daido.kanainko.wordlist.viewentity.WordViewEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ChooseWordFragment : Fragment() {

    val chooseWordViewModel: ChooseWordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChooseWordBinding
            .inflate(
                inflater,
                container,
                false
            )

        val wordGroupListAdapter = WordGroupListAdapter(requireContext())

        chooseWordViewModel
            .wordLisLiveData
            .observeForever {
                wordGroupListAdapter.submitList(it)
            }

        wordGroupListAdapter.onWordGruopClickListener = object : OnWordGruopClickListener {
            override fun click(wordGroupViewEntity: WordGroupViewEntity) {
                val dialog = ChooseWordDialog.create(wordGroupViewEntity.wordList)
                dialog.show(parentFragmentManager, "tag")
                dialog.onWordChooseDoneListener = object : OnWordChooseDoneListener {
                    override fun chooseDone(wordViewEntity: WordViewEntity) {
                        dialog.dismiss()
                        findNavController().navigate(
                            ChooseWordFragmentDirections.actionChooseWordFragmentToRecordFragment(
                                RecordWordEntity(wordGroupViewEntity.name)
                            )
                        )
                    }
                }
            }
        }

        binding.fragmentChooseWordWordGroupListRecyclerView.adapter = wordGroupListAdapter

        return binding.root
    }
}