package com.sebapp.challengeteco.ui.characterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebapp.challengeteco.databinding.FragmentCharacterListBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        itemDetail()
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                recyclerViewAdapter = RecyclerViewAdapter()
                adapter = recyclerViewAdapter
            }
        }
    }

    private fun initViewModel() {
        val viewModel = getViewModel<CharacterViewModel>()
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collect { character ->
                recyclerViewAdapter.submitData(character)
                /*when (character) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.message_error_api),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }*/
            }
        }
    }

    private fun itemDetail() {
        recyclerViewAdapter.setOnItemClickListener { character ->
            val action =
                CharacterListFragmentDirections
                    .actionCharacterListFragmentToDetailFragment(
                        character
                    )
            findNavController().navigate(action)
        }
    }
}
