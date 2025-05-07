package com.example.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booklist.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val bookList = listOf(
        Book(R.string.book_title1, R.string.book_year1, R.string.book_about1, R.string.book_webUrl1, R.drawable.image1),
        Book(R.string.book_title2, R.string.book_year2, R.string.book_about2, R.string.book_webUrl2, R.drawable.image2),
        Book(R.string.book_title3, R.string.book_year3, R.string.book_about3, R.string.book_webUrl3, R.drawable.image3),
        Book(R.string.book_title4, R.string.book_year4, R.string.book_about4, R.string.book_webUrl4, R.drawable.image4),
        Book(R.string.book_title5, R.string.book_year5, R.string.book_about5, R.string.book_webUrl5, R.drawable.image5),
        Book(R.string.book_title6, R.string.book_year6, R.string.book_about6, R.string.book_webUrl6, R.drawable.image6),
        Book(R.string.book_title7, R.string.book_year7, R.string.book_about7, R.string.book_webUrl7, R.drawable.image7),
        Book(R.string.book_title8, R.string.book_year8, R.string.book_about8, R.string.book_webUrl8, R.drawable.image8)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = BookAdapter(requireContext(), bookList) { selectedBook ->
            val bundle = Bundle().apply {
                putInt("titleRes", selectedBook.titleResourceId)
                putInt("yearRes", selectedBook.yearResourceId)
                putInt("aboutRes", selectedBook.aboutResourceId)
                putInt("imageResId", selectedBook.imageResourceId)
            }
            findNavController().navigate(R.id.action_bookFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
