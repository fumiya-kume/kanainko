package kuu.nagoya.featurewordlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.featurewordlist.databinding.FragmentChooseWordBinding
import kuu.nagoya.featurewordlist.view.dialog.ChooseWordDialog
import kuu.nagoya.featurewordlist.view.dialog.OnWordChooseDoneListener
import kuu.nagoya.featurewordlist.viewentity.WordGroupViewEntity
import kuu.nagoya.featurewordlist.viewentity.WordViewEntity
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

        val wordGroupListAdapter =
            WordGroupListAdapter(requireContext())

        chooseWordViewModel
            .wordLisLiveData
            .observeForever {
                wordGroupListAdapter.submitList(it)
            }

        wordGroupListAdapter.onWordGruopClickListener = object :
            OnWordGruopClickListener {
            override fun click(wordGroupViewEntity: WordGroupViewEntity) {
                val dialog = ChooseWordDialog.create(wordGroupViewEntity.wordList)
                dialog.show(requireFragmentManager(), "tag")
                dialog.onWordChooseDoneListener = object :
                    OnWordChooseDoneListener {
                    override fun chooseDone(wordViewEntity: WordViewEntity) {
                        dialog.dismiss()
                        //TODO 単語選択から録音画面への画面遷移を実装する
//                        findNavController().navigate(
//                            ChooseWordFragmentDirections.actionChooseWordFragmentToRecordFragment(
//                                RecordWordEntity(wordGroupViewEntity.name)
//                            )
//                        )
                    }
                }
            }
        }

        binding.fragmentChooseWordWordGroupListRecyclerView.adapter = wordGroupListAdapter

        return binding.root
    }
}