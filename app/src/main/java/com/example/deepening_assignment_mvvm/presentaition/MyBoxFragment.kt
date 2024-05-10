package com.example.deepening_assignment_mvvm.presentaition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deepening_assignment_mvvm.R
import com.example.deepening_assignment_mvvm.databinding.FragmentMyBoxBinding
import com.example.deepening_assignment_mvvm.presentaition.adapter.ImageRecyclerVIewAdapter


class MyBoxFragment : Fragment() {

    private var _binding: FragmentMyBoxBinding? = null
    private val binding get() = _binding!!

    private val selectedImages by lazy { (activity as MainActivity).getSelectedImages() }

    private lateinit var adapter: ImageRecyclerVIewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentMyBoxBinding.inflate(inflater, container, false)
        initAdapter()


        return binding.root
    }
    private fun initAdapter(){
        Log.d("repository", "$selectedImages")
        val adapter = ImageRecyclerVIewAdapter { selectedImages ->
            if(!selectedImages.isLike) {
                (activity as? MainActivity)?.removeImageFromSelectedList(selectedImages)
            }
        }
        adapter.items = selectedImages
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.notifyDataSetChanged()
    }



}