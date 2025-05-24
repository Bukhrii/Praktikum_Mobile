package com.example.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booklist.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments
        val titleRes = args?.getInt("titleRes") ?: 0
        val yearRes = args?.getInt("yearRes") ?: 0
        val aboutRes = args?.getInt("aboutRes") ?: 0
        val imageResId = args?.getInt("imageResId") ?: 0

        binding.bookTitle.setText(titleRes)
        binding.bookYear.setText(yearRes)
        binding.bookAbout.setText(aboutRes)
        binding.bookImage.setImageResource(imageResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}