package com.example.deepening_assignment_mvvm.presentaition

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deepening_assignment_mvvm.data.model.SearchImageViewModel
import com.example.deepening_assignment_mvvm.data.model.SearchImageViewModelFactory
import com.example.deepening_assignment_mvvm.databinding.FragmentSearchBinding
import com.example.deepening_assignment_mvvm.presentaition.MainActivity.Companion.TAG
import com.example.deepening_assignment_mvvm.presentaition.adapter.ImageRecyclerVIewAdapter
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    private val word by lazy { binding.etInputKeyword.text.toString() }


    private val searchImageViewModel by activityViewModels<SearchImageViewModel> {
        SearchImageViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val lastSearchWord = arguments?.getString("data") ?: ""
        Log.d(TAG, "Received word: $lastSearchWord")

        initAdapter()
        searchBtnClickListener()
        binding.etInputKeyword.setText(lastSearchWord)
        return binding.root

    }

    private fun initAdapter() {
        val adapter = ImageRecyclerVIewAdapter { document ->
            if (document.isLike) {
                (activity as? MainActivity)?.addImageToSelectedList(document)
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        searchImageViewModel.getSearchImageResponse.observe(viewLifecycleOwner) { items ->
            adapter.items = items  // Adapter에 아이템 리스트를 업데이트
            adapter.notifyDataSetChanged()  // 데이터 변경 알림
        }
    }

    private fun hideKeyBoard(){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun searchBtnClickListener() {
        binding.searchBtn.setOnClickListener {

            searchImageViewModel.getSearchImageList(word)

            hideKeyBoard()
            sendToData(word)

        }

    }

    private fun sendToData(word: String){
        val mActivity = activity as MainActivity
        mActivity.receiveData(word)
    }

    private fun loadToData(){
//        val pref = getSharedPreferences("lastWord", 0)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = SearchFragment().apply {

        }
    }
}