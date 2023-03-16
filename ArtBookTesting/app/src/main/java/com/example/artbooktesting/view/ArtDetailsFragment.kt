package com.example.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.FragmentArtDeatilsBinding
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(val glide : RequestManager) : Fragment(R.layout.fragment_art_deatils) {
    private var fragmenBinding : FragmentArtDeatilsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtDeatilsBinding.bind(view)
        fragmenBinding = binding
        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }
        // geri dönme tuşuna basınca yapılcak işler
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroy() {
        fragmenBinding = null
        super.onDestroy()
    }
}