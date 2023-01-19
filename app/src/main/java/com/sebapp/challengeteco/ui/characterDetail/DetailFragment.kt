package com.sebapp.challengeteco.ui.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sebapp.challengeteco.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemDetail()
    }

    private fun itemDetail() {
        binding.apply {
            Glide.with(imageViewPhoto)
                .load(args.character.image)
                .centerCrop()
                .into(imageViewPhoto)
            tvName.text = args.character.name
            tvStatus.text = args.character.status
            tvSpecies.text = args.character.species
            tvGender.text = args.character.gender
            tvOrigin.text = args.character.origin?.name ?: ""
        }
    }
}
