package com.example.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booklist.databinding.FragmentBookBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


class BookFragment : Fragment() {
    private val viewModel: BookViewModel by viewModels {
        BookViewModelFactory("MyCategory")
    }

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.books.collectLatest { bookList ->
                recyclerView.adapter = BookAdapter(requireContext(), bookList) { selectedBook ->
                    Timber.d("Tombol 'Tentang' ditekan untuk buku dengan judul ID: ${selectedBook.titleResourceId}")
                    val bundle = Bundle().apply {
                        putInt("titleRes", selectedBook.titleResourceId)
                        putInt("yearRes", selectedBook.yearResourceId)
                        putInt("aboutRes", selectedBook.aboutResourceId)
                        putInt("imageResId", selectedBook.imageResourceId)
                    }
                    findNavController().navigate(R.id.action_bookFragment_to_detailFragment, bundle)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
